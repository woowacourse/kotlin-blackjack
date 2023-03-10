package domain.person

import constant.BlackJackConstants.BLACK_JACK_NUMBER
import domain.card.Card
import domain.card.HandOfCards
import domain.card.strategy.GetMinSum
import domain.card.strategy.SumStrategy

abstract class Person() {
    abstract val name: String
    protected abstract val handOfCards: HandOfCards

    fun receiveCard(vararg card: Card) {
        card.forEach { handOfCards.addCard(it) }
    }

    fun showHandOfCards(): List<Card> = handOfCards.cards

    fun getTotalCardNumber(sumStrategy: SumStrategy): Int {
        return handOfCards.getTotalCardSum(sumStrategy)
    }

    abstract fun canReceiveMoreCard(): Boolean

    fun isBust(): Boolean = getTotalCardNumber(GetMinSum) > BLACK_JACK_NUMBER
}
