package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Participant {
    protected val innerCards: Cards = Cards()
    abstract val hitThreshold: Int

    fun getCards(): List<Card> {
        return innerCards.toList()
    }

    fun getTotalSum(): Int = innerCards.calculateTotalSum()

    fun addCard(card: Card) {
        if (canHit()) innerCards.add(card)
    }

    fun isBust(): Boolean {
        return getTotalSum() > BLACKJACK_BUST_LIMIT
    }

    fun isBlackJack(): Boolean {
        return innerCards.countAce() == 1 && innerCards.countScoredTen() == 1
    }

    fun canHit(): Boolean {
        return getTotalSum() < hitThreshold
    }

    companion object {
        const val BLACKJACK_BUST_LIMIT = 21
    }
}
