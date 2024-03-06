package blackjack.model.game.state

import blackjack.model.card.Deck
import blackjack.model.card.Hand

class Action(private val hand: Hand, private val deck: Deck) {
    fun hit(): Hand {
        return hand.draw(deck.dealCard())
    }
}
