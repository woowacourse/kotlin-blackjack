package domain.gamer

import domain.card.Card


class Hand(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards {

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getScore(): Int {
        var tmpSum = cards.sumOf { it.score() }
        val aceCount = aceCount()

        repeat(aceCount) {
            if (tmpSum <= BLACKJACK_SCORE) {
                return tmpSum
            }
            tmpSum -= ACE_SUBTRACT_VALUE
        }
        return tmpSum
    }

    private fun aceCount() = cards.filter { it.isAce() }
        .count()

    fun isBust(): Boolean {
        return getScore() > BLACKJACK_SCORE
    }

    companion object {
        private const val BLACKJACK_SCORE: Int = 21
        private const val ACE_SUBTRACT_VALUE = 10
    }
}