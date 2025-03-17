package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.score.Score

abstract class Participant {
    val innerHand: Hand = Hand()
    abstract val hitThreshold: Int

    fun getCards(): List<Card> {
        return innerHand.toList()
    }

    fun addCard(card: Card) {
        if (canHit()) innerHand.add(card)
    }

    fun isBust(): Boolean {
        return Score(this) > BLACKJACK_BUST_LIMIT
    }

    fun isBlackJack(): Boolean {
        return innerHand.countAce() == 1 && innerHand.countScoredTen() == 1
    }

    fun canHit(): Boolean {
        return Score(this) < hitThreshold
    }

    companion object {
        const val BLACKJACK_BUST_LIMIT = 21
    }
}
