package domain.person

import constant.BlackJackConstants.BLACK_JACK
import domain.card.Card
import domain.card.HandOfCards
import domain.card.strategy.SumStrategy.getMinSum

abstract class Person() {
    abstract val name: String
    protected abstract val handOfCards: HandOfCards

    fun receiveCard(vararg card: Card) {
        card.forEach { handOfCards.addCard(it) }
    }

    fun showHandOfCards(): List<Card> = handOfCards.cards

    fun getTotalCardNumber(getSum: HandOfCards.() -> Int): Int {
        return handOfCards.getSum()
    }

    abstract fun canReceiveMoreCard(): Boolean

    fun isBust(): Boolean = getTotalCardNumber { getMinSum() } > BLACK_JACK
}
