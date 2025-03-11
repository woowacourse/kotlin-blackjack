package blackjack.domain

import blackjack.domain.Score.Companion.SCORE_BLACKJACK

sealed interface Score : Comparable<Score> {
    val value: Int

    override fun compareTo(other: Score): Int = value.compareTo(other.value)

    @JvmInline
    value class Hittable(
        override val value: Int,
    ) : Score

    object Blackjack : Score {
        override val value: Int = SCORE_BLACKJACK
    }

    @JvmInline
    value class Bust(
        override val value: Int,
    ) : Score

    companion object {
        const val SCORE_BLACKJACK: Int = 21
    }
}

fun Score(cards: List<Card>): Score {
    if (cards.isEmpty()) return Score.Hittable(0)
    val possibleScores = ScoreCalculator.possibleScoreOf(*(cards.toTypedArray()))
    if (possibleScores.all { score: Int -> score > SCORE_BLACKJACK }) return Score.Bust(possibleScores.min())
    val score = possibleScores.filter { score -> score <= SCORE_BLACKJACK }.max()
    return when {
        score == 21 && cards.size == 2 -> return Score.Blackjack
        else -> return Score.Hittable(score)
    }
}
