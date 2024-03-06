package blackjack.model.game

import blackjack.model.card.Deck
import blackjack.model.card.Hand

class Action(private val hand: Hand, private val deck: Deck) {
    fun hit(): Boolean {
        hand.draw(deck.dealCard())
        return true
    }

    fun stay(): Boolean {
        return false
    }
}
