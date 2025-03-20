package blackjack.domain.model.service

import blackjack.domain.model.Card
import blackjack.domain.model.Deck
import blackjack.domain.model.Money
import blackjack.domain.model.betting.BettingPlayer
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
import blackjack.view.GameView

class BlackJackService private constructor(
    val playingParticipants: PlayingParticipants,
    private val gameView: GameView,
) {
    private var deck = Deck.from()

    fun bettingPlayers(playerNames: Set<String>): BettingPlayers {
        val bettingPlayers =
            playerNames.map { name ->
                val money = Money(retryEvent { gameView.readPlayerBetAmount(name) })
                BettingPlayer(name, money)
            }
        return BettingPlayers(bettingPlayers)
    }

    fun dealInitialCard() {
        repeat(START_CARD_COUNT) {
            dealParticipantsCards()
        }
        gameView.printInitialDeals(playingParticipants)
        gameView.printParticipantsStatus((playingParticipants))
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

    fun playPlayers() {
        playingParticipants.players.forEach { participant ->
            playHand(participant)
        }
    }

    private fun playHand(playingParticipant: PlayingParticipant) {
        if (playingParticipant.isFinished()) return
        val choice = retryEvent { gameView.readPlayerAction(playingParticipant) }
        if (UserChoice.STAY == choice) {
            playingParticipant.stay()
            if (playingParticipant.isStarted()) gameView.printPlayerStatus(playingParticipant)
            return
        }
        playingParticipant.acceptCard(deck.draw() ?: drawNewDeckCard())
        gameView.printPlayerStatus(playingParticipant)
        playHand(playingParticipant)
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

    private fun <T> retryEvent(event: () -> T): T {
        while (true) {
            kotlin.runCatching { event() }
                .onSuccess { return it }
                .onFailure { gameView.printErrorMessage(it.message ?: it.stackTraceToString()) }
        }
    }

    companion object {
        fun from(
            playersName: List<String>,
            gameView: GameView,
        ): BlackJackService {
            val playingPlayers =
                playersName.map { name -> PlayingPlayer(Initial(PlayerStay(), Hands()), name) }
            val playingDealer = PlayingDealer(Initial(DealerStay(), Hands()))
            return BlackJackService(PlayingParticipants(playingDealer, playingPlayers), gameView)
        }

        const val START_CARD_COUNT = 2
    }
}
