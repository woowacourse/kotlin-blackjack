package blackjack.domain

import blackjack.domain.BlackJack.Companion.blackjackScore

class ParticipantHand {
    private val _cards: MutableList<Card> by lazy { mutableListOf() }
    val cards: List<Card>
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun calculateTotalScore(): Int {
        val score = _cards.fold(0) { total, card -> total + card.getScore() }
        return calculateAceScore(score)
    }

    private fun calculateAceScore(score: Int): Int =
        if (hasAce() && (score + BONUS_SCORE) <= blackjackScore()) score + BONUS_SCORE else score

    private fun hasAce(): Boolean = _cards.any(Card::isAce)

    companion object {
        private const val BONUS_SCORE = 10
    }
}
