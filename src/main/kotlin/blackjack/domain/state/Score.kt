package blackjack.domain.state

class Score(hardScore: Int, hasAce: Boolean = false) {
    private val isAvailableSoftScore: Boolean = hasAce && hardScore <= SOFT_ACE_AVAILABLE_SCORE

    val value = when {
        isAvailableSoftScore -> hardScore + DIFF_SOFT_AND_HARD_ACE_SCORE
        else -> hardScore
    }

    val isBlackJack = value == BLACKJACK_NUMBER

    val isBust: Boolean = value > BLACKJACK_NUMBER

    operator fun compareTo(score: Score): Int = value.compareTo(score.value)

    operator fun plus(score: Score): Score = Score(value + score.value)

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val DIFF_SOFT_AND_HARD_ACE_SCORE = 10
        private const val SOFT_ACE_AVAILABLE_SCORE = 11
    }
}
