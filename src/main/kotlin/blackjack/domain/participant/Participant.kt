package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Participant {
    val totalSum: Int
        get() = innerCards.calculateTotalSum()

    protected val innerCards: Cards = Cards()

    abstract val hitThreshold: Int

    fun getCards(): List<Card> {
        return innerCards.toList()
    }

    fun addCard(card: Card) {
        if (canHit()) innerCards.add(card)
    }

    fun isBust(): Boolean {
        return totalSum > BLACKJACK_BUST_LIMIT
    }

    fun isBlackJack(): Boolean {
        return innerCards.countAce() == 1 && innerCards.countScoredTen() == 1
    }

    fun canHit(): Boolean {
        return totalSum < hitThreshold
    }

    companion object {
        const val BLACKJACK_BUST_LIMIT = 21
    }
}
