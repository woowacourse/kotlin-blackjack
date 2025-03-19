package blackjack.domain.model.service

import blackjack.domain.model.Deck
import blackjack.domain.model.hand.Hands
import blackjack.domain.model.hand.state.Initial
import blackjack.domain.model.hand.strategy.DealerStay
import blackjack.domain.model.hand.strategy.PlayerStay
import blackjack.domain.model.playing.PlayingDealer
import blackjack.domain.model.playing.PlayingParticipants
import blackjack.domain.model.playing.PlayingPlayer

class InitService(private val playersName: Set<String>, private val deck: Deck) {
    fun initPlayingParticipants(): PlayingParticipants {
        val playingPlayers =
            playersName.map { name -> PlayingPlayer(Initial(PlayerStay(), Hands()), name) }
        val playingDealer = PlayingDealer(Initial(DealerStay(), Hands()))
        return PlayingParticipants(playingDealer, playingPlayers)
    }

    fun dealInitialCard(playingParticipants: PlayingParticipants) {
        repeat(START_CARD_COUNT) {
            playingParticipants.dealInitialCard(deck)
        }
    }

    companion object {
        const val START_CARD_COUNT = 2
    }
}
