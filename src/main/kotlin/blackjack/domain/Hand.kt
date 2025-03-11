package blackjack.domain

import blackjack.domain.Participant.Companion.BUST_THRESHOLD

class Hand {
    private val cards: MutableList<Card> = mutableListOf()

    fun getCards() : List<Card> = cards.toList()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCardSum() : Int {
        return calculateSum()
    }

    private fun calculateSum(): Int {
        var sum = cards.sumOf { it.getScore() }
        var aceCount = cards.count { it.rank == Rank.ACE }

        while (sum + ACE_SCORE_DIFFERENCE <= BUST_THRESHOLD && aceCount > 0) {
            sum += ACE_SCORE_DIFFERENCE
            aceCount--
        }

        return sum
    }
    companion object {
        private const val ACE_HIGH = 11
        private const val ACE_LOW = 1
        private const val ACE_SCORE_DIFFERENCE = ACE_HIGH - ACE_LOW
    }
}