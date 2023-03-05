package model

import misc.GameRule

class NumberStrategy() {
    fun judgeAce(sumOfCards: Int): Int {
        if (sumOfCards + 11 > GameRule.WINNING_NUMBER) return ACE_ONE
        else return ACE_ELEVEN
    }

    companion object {
        private const val ACE_ONE = 1
        private const val ACE_ELEVEN = 11
    }
}
