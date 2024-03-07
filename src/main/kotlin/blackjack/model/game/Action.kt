package blackjack.model.game

import blackjack.model.card.Deck
import blackjack.model.card.Hand

class Action(private val hand: Hand) {
    fun hit(): Boolean {
        hand.draw(Deck.dealCard())
        return true
    }

    fun stay(): Boolean {
        return false
    }
}
