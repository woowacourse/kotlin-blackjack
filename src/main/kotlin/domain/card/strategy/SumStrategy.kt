package domain.card.strategy

import constant.BlackJackConstants.BIG_ACE
import constant.BlackJackConstants.BLACK_JACK
import constant.BlackJackConstants.SMALL_ACE
import domain.card.HandOfCards

object SumStrategy {
    fun HandOfCards.getMinSum() = getExceptAceSum() + countAce()

    fun HandOfCards.getAppropriateSum(): Int {
        val aceCount = countAce()
        val exceptAceSum = getExceptAceSum()
        val availableMax = BLACK_JACK - exceptAceSum
        return exceptAceSum + calculateAceSum(availableMax, aceCount)
    }

    private fun calculateAceSum(availableMax: Int, aceCount: Int): Int {
        if (aceCount == 0) return 0
        val max = BIG_ACE + (aceCount - 1) * SMALL_ACE
        val min = aceCount * SMALL_ACE
        if (max > availableMax) return min
        return max
    }
}
