package blackjack.domain.card

import blackjack.domain.Score

class Hand {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun score(): Score {
        val score = _cards.sumOf { it.getNumber() }
        val aceAdjustedScore = Score(score + ACE_VALUE_DIFFERENCE)
        if (hasAce() && !aceAdjustedScore.isBust()) {
            return aceAdjustedScore
        }
        return Score(score)
    }

    fun isBlackjack(): Boolean = _cards.size == 2 && score().isPerfectScore()

    private fun hasAce(): Boolean = _cards.any { it.isAce() }

    companion object {
        private const val ACE_VALUE_DIFFERENCE = 10
    }
}
