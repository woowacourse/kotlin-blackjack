package blackjack.domain.score

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Participant
import java.lang.IllegalStateException

data class Score(private val cards: List<Card>) {
    constructor(participant: Participant) : this(participant.getCards())

    val value: Int
        get() = calculateTotalSum()

    operator fun compareTo(score: Score): Int {
        return value.compareTo(score.value)
    }

    operator fun compareTo(value: Int): Int {
        return this.value.compareTo(value)
    }

    private fun getRawScore(): Int {
        return cards.sumOf {
            if (it.rank == Rank.ACE) {
                ACE_SPECIFIC_SCORE
            } else {
                it.getScore()
            }
        }
    }

    private fun countAce(): Int {
        return cards.count { it.rank == Rank.ACE }
    }

    private fun calculateTotalSum(): Int {
        var score = getRawScore()
        var aceCount = countAce()
        while (score > BLACKJACK_BUST_LIMIT && aceCount > 0) {
            score -= ACE_SPECIFIC_SCORE - Rank.ACE.score
            aceCount--
        }

        return score
    }

    fun isEqualTo(other: Score): Boolean {
        return value == other.value
    }

    fun compare(other: Score): GameResult {
        return when {
            this > other -> GameResult.WIN
            this < other -> GameResult.LOSE
            this.isEqualTo(other) -> GameResult.DRAW
            else -> throw IllegalStateException("")
        }
    }

    companion object {
        const val BLACKJACK_BUST_LIMIT = 21
        private const val ACE_SPECIFIC_SCORE = 11
    }
}
