package blackjack.domain.score

import blackjack.domain.card.CardNumber
import blackjack.domain.person.Hand

object ScoreCalculator {
    private const val ACE_BASE_SCORE = 10
    private const val ACE_OTHER_SCORE = 11
    private const val BLACKJACK_BASE_SCORE = 21

    fun calculate(hand: Hand): Int {
        val values = hand.cards.map { if (it.number == CardNumber.ACE) ACE_OTHER_SCORE else it.number.value }
        val sum = values.sum()
        return values.fold(sum) { acc, number ->
            if (acc > BLACKJACK_BASE_SCORE && number == ACE_OTHER_SCORE) acc - ACE_BASE_SCORE else acc
        }
    }
}
