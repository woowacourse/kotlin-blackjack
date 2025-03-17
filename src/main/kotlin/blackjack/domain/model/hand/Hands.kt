package blackjack.domain.model.hand

import blackjack.domain.model.Card
import blackjack.domain.model.Rank
import blackjack.domain.model.Score

class Hands(private val _cards: List<Card>) {
    constructor(vararg card: Card) : this(card.toList())

    val cards get() = _cards.map { it.copy() }

    val size get() = _cards.size

    fun extractCards(count: Int): List<Card> = cards.take(count)

    fun nextHand(card: Card) = Hands(cards + card)

    fun score(): Score {
        val score = Score(cards.sumOf { it.rank.score })
        return score + getBonusScore(score = score)
    }

    fun isBlackJack(count: Int) = score().isBlackJack(count)

    fun isBustScore() = score().isBustScore()

    fun isMaxScore() = score().isMaxScore()

    fun isDealerStay() = score().isDealerStay()

    private fun getBonusScore(score: Score): Int {
        val totalScore = score + BONUS_SCORE
        if (totalScore.isBustScore() && hasAce()) return BONUS_SCORE
        return 0
    }

    private fun hasAce(): Boolean = this.cards.any { it.rank == Rank.ACE }

    companion object {
        private const val BONUS_SCORE = 10
    }
}
