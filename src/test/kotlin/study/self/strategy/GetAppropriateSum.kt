/*
package domain.card.strategy

import constant.BlackJackConstants.BIG_ACE
import constant.BlackJackConstants.BLACK_JACK
import constant.BlackJackConstants.SMALL_ACE
import domain.card.HandOfCards

object GetAppropriateSum : SumStrategy {
    override fun getSum(handOfCards: HandOfCards): Int {
        val aceCount = handOfCards.countAce()
        val exceptAceSum = handOfCards.getExceptAceSum()
        val availableMax = BLACK_JACK - exceptAceSum
        return exceptAceSum + calculateAceSum(availableMax, aceCount)
    }

    private fun calculateAceSum(availableMax: Int, aceCount: Int, currentSum: Int = 0): Int {
        if (availableMax <= aceCount * SMALL_ACE) return aceCount * SMALL_ACE
        val max = currentSum + aceCount * BIG_ACE
        if (max > availableMax) {
            return calculateAceSum(availableMax - SMALL_ACE, aceCount - 1, currentSum + SMALL_ACE)
        }
        return max
    }
}
*/
