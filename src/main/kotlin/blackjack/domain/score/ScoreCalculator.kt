package blackjack.domain.score

import blackjack.domain.card.CardNumber
import blackjack.domain.person.Hand

object ScoreCalculator {
    private const val ACE_BASE_SCORE = 10
    private const val BLACKJACK_BASE_SCORE = 21

    fun calculate(hand: Hand): Int {
        return hand.cards.fold(0) { sum, card ->
            val base = sum + card.number.value + (ACE_BASE_SCORE.takeIf { card.number == CardNumber.ACE } ?: 0)
            base.takeIf { it <= BLACKJACK_BASE_SCORE } ?: (sum + CardNumber.ACE.value)
        }
    }
}
