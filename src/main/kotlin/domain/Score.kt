package domain

class Score(val cards: Cards) {
    private val sum = cards.sum()

    init {
        require(sum >= MINIMUM_SUM) { MINIMUM_SUM_ERROR }
    }

    fun isBurst(): Boolean = sum > BLACKJACK_NUMBER

    fun isBlackJack(): Boolean = cards.size == BLACKJACK_CONDITION_SIZE && sum == BLACKJACK_CONDITION_SIZE

    fun getValue(): Int {
        if (cards.isContainAce()) {
            return getScoreWithAce()
        }
        return sum
    }

    private fun getScoreWithAce(): Int {
        if (sum + ACE_ADDITIONAL_VALUE > BLACKJACK_NUMBER) return sum
        return sum + ACE_ADDITIONAL_VALUE
    }

    companion object {
        private const val MINIMUM_SUM = 2
        private const val MINIMUM_SUM_ERROR = "카드의 합은 최소 2 이상입니다"
        private const val ACE_ADDITIONAL_VALUE = 10
        private const val BLACKJACK_CONDITION_SIZE = 2
        const val BLACKJACK_NUMBER = 21
    }
}
