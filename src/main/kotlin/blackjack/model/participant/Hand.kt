package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardRank

class Hand(
    initialCards: List<Card> = emptyList(),
) {
    private val _cards: MutableList<Card> = initialCards.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun score(): Int {
        val hardScore = cards.sumOf { card -> card.rank.score }
        val softScore = softScore(cards, hardScore)

        return when {
            isBust(hardScore) -> hardScore
            isBust(softScore) -> hardScore
            else -> softScore
        }
    }

    fun isBust(score: Int = score()): Boolean = score > BUST_CRITERIA

    private fun softScore(
        cards: List<Card>,
        hardScore: Int,
    ): Int {
        val containsAce = cards.any { card -> card.rank == CardRank.ACE }
        return if (containsAce) hardScore + SOFT_OFFSET_SCORE else hardScore
    }

    companion object {
        private const val BUST_CRITERIA = 21
        private const val SOFT_OFFSET_SCORE = 10
    }
}
