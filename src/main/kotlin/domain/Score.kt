package domain

class Score(sum: Int, hasAce: Boolean) : Comparable<Score> {
    val value: Int

    init {
        val sumWithAceAdditionalValue = sum + ACE_ADDITIONAL_VALUE
        value = if (hasAce && sumWithAceAdditionalValue <= BlackJackGame.BLACKJACK_NUMBER) {
            sumWithAceAdditionalValue
        } else {
            sum
        }
    }

    override fun compareTo(other: Score): Int = when {
        this.value > other.value -> 1
        this.value < other.value -> -1
        else -> 0
    }

    companion object {
        private const val ACE_ADDITIONAL_VALUE = 10
    }
}
