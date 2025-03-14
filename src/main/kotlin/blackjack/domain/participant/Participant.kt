package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.score.Score

abstract class Participant {
    val innerCards: Cards = Cards()
    abstract val hitThreshold: Int

    fun getCards(): List<Card> {
        return innerCards.toList()
    }

    fun addCard(card: Card) {
        if (canHit()) innerCards.add(card)
    }

    fun isBust(): Boolean {
        return Score(this) > BLACKJACK_BUST_LIMIT
    }

    fun isBlackJack(): Boolean {
        return innerCards.countAce() == 1 && innerCards.countScoredTen() == 1
    }

    fun canHit(): Boolean {
        return Score(this) < hitThreshold
    }

    companion object {
        const val BLACKJACK_BUST_LIMIT = 21
    }
}
