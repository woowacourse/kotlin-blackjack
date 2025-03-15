package blackjack.domain.model.service

import InitialDealer
import InitialParticipants
import InitialPlayer
import blackjack.domain.model.Deck
import blackjack.domain.model.Hands
import blackjack.domain.model.Hands.Companion.START_CARD_COUNT

class InitService(private val playersName: Set<String>, private val deck: Deck) {
    fun initPlayingParticipants(): InitialParticipants {
        val initialPlayers =
            playersName.map { name ->
                InitialPlayer(Hands(List(START_CARD_COUNT) { deck.draw() }), name)
            }
        val playingDealer = InitialDealer(Hands(List(START_CARD_COUNT) { deck.draw() }))
        return InitialParticipants(playingDealer, initialPlayers)
    }
}
