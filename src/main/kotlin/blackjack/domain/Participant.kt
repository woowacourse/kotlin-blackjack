package blackjack.domain

import blackjack.domain.state.PlayingState
import blackjack.domain.state.Ready

abstract class Participant(val name: String) {
    val hand: Hand = Hand(emptyList())
    var state: PlayingState = Ready(hand)

    fun draw(deck: Deck) {
        val drawnCard = deck.draw()
        hand.addCard(drawnCard)
    }

    fun changeState(state: PlayingState) {
        this.state = state
    }
}
