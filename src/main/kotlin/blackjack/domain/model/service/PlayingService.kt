package blackjack.domain.model.service

import blackjack.domain.model.Deck
import blackjack.domain.model.betting.BettingPlayers
import blackjack.domain.model.hand.HandState
import blackjack.domain.model.playing.PlayingParticipant
import blackjack.domain.model.playing.PlayingParticipants
import blackjack.domain.model.profit.ProfitParticipants

class PlayingService(val playingParticipants: PlayingParticipants, private val deck: Deck) {
    fun playPlayers(
        onHandAction: (PlayingParticipant) -> HandState,
        onStartStay: (PlayingParticipant) -> Unit,
        onPlayerState: (PlayingParticipant) -> Unit,
    ) {
        playingParticipants.players.forEach { participant ->
            playHand(participant, onHandAction, onStartStay, onPlayerState)
        }
    }

    private fun playHand(
        playingParticipant: PlayingParticipant,
        onHandAction: (PlayingParticipant) -> HandState,
        onStartStay: (PlayingParticipant) -> Unit,
        onPlayerState: (PlayingParticipant) -> Unit,
    ) {
        if (playingParticipant.handsState.isFinished()) return
        val choice = onHandAction(playingParticipant)
        if (HandState.STAY == choice) {
            playingParticipant.handsState.stay()
            if (playingParticipant.handsState.cards().size == 2) onStartStay(playingParticipant)
            return
        }
        playingParticipant.acceptCard(deck.draw())
        onPlayerState(playingParticipant)
        playHand(playingParticipant, onHandAction, onStartStay, onPlayerState)
    }

    fun playDealer(onDealerHitsState: () -> Unit) {
        val playingDealer = playingParticipants.dealer
        if (playingDealer.handsState.isFinished()) return
        onDealerHitsState()
        playingDealer.acceptCard(deck.draw())
        playDealer(onDealerHitsState)
    }

    fun calculateProfitPlayers(bettingPlayers: BettingPlayers): ProfitParticipants =
        playingParticipants.toProfitParticipants(bettingPlayers)
}
