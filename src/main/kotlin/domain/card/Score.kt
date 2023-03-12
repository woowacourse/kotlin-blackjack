package domain.card

class Score(val cards: Cards) {
    private val sum: Int
        get() = cards.sum()

    fun isBurst(): Boolean = getValue() > BLACKJACK_NUMBER

    fun isBlackJack(): Boolean = (cards.size == BLACKJACK_CONDITION_SIZE) && (getValue() == BLACKJACK_NUMBER)

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
        private const val ACE_ADDITIONAL_VALUE = 10
        private const val BLACKJACK_CONDITION_SIZE = 2
        const val BLACKJACK_NUMBER = 21
    }
}
