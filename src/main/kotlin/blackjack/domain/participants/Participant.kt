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
        return score() == GameRule.BLACKJACK_SCORE && _hand.size == GameRule.FIRST_TURN_DRAW_AMOUNT
    }

    fun isBust(): Boolean = score() > GameRule.BLACKJACK_SCORE

    fun score(): Int = ScoreCalculator.calculate(hand)

    fun getDrawAmount(): Int {
        if (hand.isEmpty()) {
            return GameRule.FIRST_TURN_DRAW_AMOUNT
        }

        if (canHit()) {
            return GameRule.HIT_DRAW_AMOUNT
        }

        throw IllegalArgumentException("[ERROR] 카드를 뽑을 수 없습니다.")
    }
}
