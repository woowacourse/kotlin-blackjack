package blackjack.domain.state

class Score(val value: Int) {
    val isBlackJack = value == BLACKJACK_NUMBER

    val isBust: Boolean = value > BLACKJACK_NUMBER

    operator fun plus(score: Score): Score = Score(value + score.value)

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
