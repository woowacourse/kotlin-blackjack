package blackjack.model.hand

import blackjack.model.card.Card
import blackjack.model.card.CardRank
import blackjack.model.hand.HandState.BUST

class Hand(
    initialCards: List<Card> = emptyList(),
) {
    private val _cards: MutableList<Card> = initialCards.toMutableList()
    val cards: List<Card> get() = _cards.toList()
    val state: HandState get() = HandState.from(score(), cards.size)

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun score(): Int {
        val hardScore = cards.sumOf { card -> card.rank.score }
        val softScore = softScore(cards, hardScore)

        return when {
            hardScore.isBustScore() -> hardScore
            softScore.isBustScore() -> hardScore
            else -> softScore
        }
    }

    private fun Int.isBustScore(): Boolean = HandState.from(this, cards.size) == BUST

    private fun softScore(
        cards: List<Card>,
        hardScore: Int,
    ): Int {
        val containsAce = cards.any { card -> card.rank == CardRank.ACE }
        return if (containsAce) hardScore + SOFT_OFFSET_SCORE else hardScore
    }

    companion object {
        private const val SOFT_OFFSET_SCORE = 10
    }
}
