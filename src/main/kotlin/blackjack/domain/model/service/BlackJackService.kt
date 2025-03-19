package blackjack.domain.model.service

import blackjack.domain.model.Card
import blackjack.domain.model.Deck
import blackjack.domain.model.betting.BettingPlayers
import blackjack.domain.model.hand.Hands
import blackjack.domain.model.hand.UserChoice
import blackjack.domain.model.hand.state.Initial
import blackjack.domain.model.hand.strategy.DealerStay
import blackjack.domain.model.hand.strategy.PlayerStay
import blackjack.domain.model.playing.PlayingDealer
import blackjack.domain.model.playing.PlayingParticipant
import blackjack.domain.model.playing.PlayingParticipants
import blackjack.domain.model.playing.PlayingPlayer
import blackjack.domain.model.profit.ProfitParticipants

class BlackJackService private constructor(val playingParticipants: PlayingParticipants) {
    private var deck = Deck.from()

    fun dealInitialCard(
        initialDeal: (PlayingParticipants) -> Unit,
        onPlayerStates: (PlayingParticipants) -> Unit,
    ) {
        repeat(START_CARD_COUNT) {
            initialDeal(playingParticipants)
            dealParticipantsCards()
        }
        initialDeal(playingParticipants)
        onPlayerStates(playingParticipants)
        onPlayerStates(playingParticipants)
    }

    private fun dealParticipantsCards() {
        playingParticipants.participants.forEach { playingParticipant ->
            playingParticipant.acceptCard(deck.draw() ?: drawNewDeckCard())
        }
    }

    private fun drawNewDeckCard(): Card {
        deck = Deck.from()
        return requireNotNull(deck.draw()) { "새로운 덱에서 카드가 없으면 안됩니다." }
    }

    fun playPlayers(
        onHandAction: (PlayingParticipant) -> UserChoice,
        onPlayerState: (PlayingParticipant) -> Unit,
    ) {
        playingParticipants.players.forEach { participant ->
            playHand(participant, onHandAction, onPlayerState)
        }
    }

    private fun playHand(
        playingParticipant: PlayingParticipant,
        onUserAction: (PlayingParticipant) -> UserChoice,
        onPlayerState: (PlayingParticipant) -> Unit,
    ) {
        if (playingParticipant.isFinished()) return
        val choice = onUserAction(playingParticipant)
        if (UserChoice.STAY == choice) {
            playingParticipant.stay()
            if (playingParticipant.isStarted()) onPlayerState(playingParticipant)
            return
        }
        playingParticipant.acceptCard(deck.draw() ?: drawNewDeckCard())
        onPlayerState(playingParticipant)
        playHand(playingParticipant, onUserAction, onPlayerState)
    }

    fun playDealer(onDealerHitsState: () -> Unit) {
        onDealerHitsState()
        val playingDealer = playingParticipants.dealer
        if (playingDealer.isFinished()) return
        onDealerHitsState()
        playingDealer.acceptCard(deck.draw() ?: drawNewDeckCard())
        playDealer(onDealerHitsState)
    }

    fun toProfitPlayers(bettingPlayers: BettingPlayers): ProfitParticipants = playingParticipants.toProfitParticipants(bettingPlayers)

    companion object {
        fun from(playersName: List<String>): BlackJackService {
            val playingPlayers =
                playersName.map { name -> PlayingPlayer(Initial(PlayerStay(), Hands()), name) }
            val playingDealer = PlayingDealer(Initial(DealerStay(), Hands()))
            return BlackJackService(PlayingParticipants(playingDealer, playingPlayers))
        }

        const val START_CARD_COUNT = 2
    }
}
