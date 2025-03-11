package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Participant {
    val totalSum: Int
        get() = cards.calculateTotalSum()

    val cards: Cards = Cards()

    abstract val hitThreshold: Int

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean {
        return totalSum > BLACKJACK_BUST_LIMIT
    }

    fun isBlackJack(): Boolean {
        return cards.countAce() == 1 && cards.countScoredTen() == 1
    }

    fun canHit(): Boolean {
        return totalSum < hitThreshold
    }

    companion object {
        const val BLACKJACK_BUST_LIMIT = 21
    }
}
