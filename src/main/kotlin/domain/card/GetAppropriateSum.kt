package domain.card

import domain.constant.BIG_ACE
import domain.constant.BLACK_JACK
import domain.constant.SMALL_ACE

class GetAppropriateSum : SumStrategy {
    override fun getSum(handOfCards: HandOfCards): Int {
        val aceCount = handOfCards.countAce()
        val exceptAceSum = handOfCards.getExceptAceSum()
        val availableMax = BLACK_JACK - exceptAceSum
        return exceptAceSum + calculateAceSum(availableMax, aceCount)
    }

    private fun calculateAceSum(availableMax: Int, aceCount: Int, currentSum: Int = 0): Int {
        val max = currentSum + aceCount * BIG_ACE
        if (max > availableMax) {
            return calculateAceSum(availableMax - SMALL_ACE, aceCount - 1, currentSum + SMALL_ACE)
        }
        return max
    }
}
