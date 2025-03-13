package blackjack.domain

import blackjack.domain.Score.Companion.SCORE_MAX_CAN_HAVE

sealed interface Score : Comparable<Score> {
    val value: Int

    override fun compareTo(other: Score): Int = value.compareTo(other.value)

    @JvmInline
    value class Hittable(
        override val value: Int,
    ) : Score

    object Max : Score {
        override val value: Int = SCORE_MAX_CAN_HAVE
    }

    object Blackjack : Score {
        override val value: Int = SCORE_MAX_CAN_HAVE
    }

    @JvmInline
    value class Bust(
        override val value: Int,
    ) : Score

    companion object {
        const val SCORE_MAX_CAN_HAVE: Int = 21
    }
}

fun Score(cards: List<Card>): Score {
    if (cards.isEmpty()) return Score.Hittable(0)
    val possibleScores = ScoreCalculator.possibleScoreOf(*(cards.toTypedArray()))
    if (possibleScores.all { score: Int -> score > SCORE_MAX_CAN_HAVE }) return Score.Bust(possibleScores.min())
    val score = possibleScores.filter { score -> score <= SCORE_MAX_CAN_HAVE }.max()
    return when {
        score == 21 && cards.size == 2 -> return Score.Blackjack
        score == 21 && cards.size != 2 -> return Score.Max
        else -> return Score.Hittable(score)
    }
}
