package blackjack.domain.gamer

import blackjack.domain.card.Card

abstract class Gamer(val name: String, val hand: Hand = Hand()) {
    fun draw(card: Card) {
        hand.addCard(card)
    }

    fun score(): Int {
        return hand.totalScore()
    }

    fun isBust(): Boolean {
        return hand.isBust()
    }
}
