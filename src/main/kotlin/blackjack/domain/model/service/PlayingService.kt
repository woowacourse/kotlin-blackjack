package blackjack.domain.model.service

import blackjack.domain.model.Deck
import blackjack.domain.model.HandState
import blackjack.domain.model.betting.BettingPlayers
import blackjack.domain.model.playing.PlayingParticipant
import blackjack.domain.model.playing.PlayingParticipants
import blackjack.domain.model.profit.ProfitParticipants

class PlayingService(val playingParticipants: PlayingParticipants, private val deck: Deck) {
    fun playPlayers(
        onHandAction: (PlayingParticipant) -> HandState,
        onStartStay: (PlayingParticipant) -> Unit,
        onPlayerState: (PlayingParticipant) -> Unit,
    ) {
        playingParticipants.playingPlayers.forEach { participant ->
            playHand(participant, onHandAction, onStartStay, onPlayerState)
        }
    }

    private fun playHand(
        playingParticipant: PlayingParticipant,
        onHandAction: (PlayingParticipant) -> HandState,
        onStartStay: (PlayingParticipant) -> Unit,
        onPlayerState: (PlayingParticipant) -> Unit,
    ) {
        if (playingParticipant.getHandsState() != HandState.HIT) return
        val choice = onHandAction(playingParticipant)
        if (HandState.STAY == choice) {
            if (playingParticipant.isStartCardCount()) onStartStay(playingParticipant)
            return
        }
        playingParticipant.acceptCard(deck.draw())
        onPlayerState(playingParticipant)
        playHand(playingParticipant, onHandAction, onStartStay, onPlayerState)
    }

    fun playDealer(onDealerHitsState: () -> Unit) {
        val playingDealer = playingParticipants.playingDealer
        while (playingDealer.isHit()) {
            onDealerHitsState()
            playingDealer.acceptCard(deck.draw())
        }
    }

    fun calculateProfitPlayers(bettingPlayers: BettingPlayers): ProfitParticipants =
        playingParticipants.toMatchPlayers().toProfitPlayers(bettingPlayers)
}
