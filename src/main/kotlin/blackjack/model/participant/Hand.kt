package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardRank
import blackjack.model.participant.HandState.ALIVE
import blackjack.model.participant.HandState.BUST

class Hand(
    initialCards: List<Card> = emptyList(),
) {
    private val _cards: MutableList<Card> = initialCards.toMutableList()
    val cards: List<Card> get() = _cards.toList()
    private var _state: HandState = ALIVE
    val state: HandState get() = _state

    fun addAll(cards: List<Card>) {
        _cards.addAll(cards)
        _state = HandState.from(score(), cards.size)
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

    private fun isBust(score: Int): Boolean = HandState.from(score, cards.size) == BUST

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
