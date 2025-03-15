package blackjack.domain.participants

import blackjack.const.GameRule
import blackjack.domain.ScoreCalculator
import blackjack.domain.card.Card

abstract class Participant(initialHand: List<Card> = emptyList()) {
    private val _hand: MutableList<Card> = initialHand.map { it.copy() }.toMutableList()

    val hand: List<Card>
        get() = _hand.toList()

    abstract fun canHit(): Boolean

    fun addCard(card: Card) {
        _hand.add(card)
    }

    fun isBlackjack(): Boolean {
        return score() == GameRule.BLACKJACK_SCORE && _hand.size == FIRST_TURN_DRAW_AMOUNT
    }

    fun isBust(): Boolean = score() > GameRule.BLACKJACK_SCORE

    fun score(): Int = ScoreCalculator.calculate(hand)

    fun getDrawAmount(): Int {
        if (hand.isEmpty()) {
            return FIRST_TURN_DRAW_AMOUNT
        }

        if (canHit()) {
            return HIT_DRAW_AMOUNT
        }

        return 0
    }

    companion object {
        private const val FIRST_TURN_DRAW_AMOUNT = 2
        private const val HIT_DRAW_AMOUNT = 1
    }
}
