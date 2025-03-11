package blackjack.domain

sealed interface HandState : Comparable<HandState> {
    val score: Int

    override fun compareTo(other: HandState): Int = score.compareTo(other.score)

    class Score(
        override val score: Int,
    ) : HandState

    object Blackjack : HandState {
        override val score: Int = 21
    }

    object Bust : HandState {
        override val score: Int = SCORE_BUSTED
    }

    companion object {
        private const val SCORE_BUSTED = -1
    }
}

fun HandState(cards: List<Card>): HandState {
    if (cards.isEmpty()) return HandState.Score(0)
    val possibleScores = ScoreCalculator.possibleScoreOf(*(cards.toTypedArray()))
    val score = possibleScores.sortedDescending().firstOrNull { score: Int -> score <= 21 }
    return when {
        score == null -> return HandState.Bust
        score == 21 && cards.size == 2 -> return HandState.Blackjack
        else -> return HandState.Score(score)
    }
}
