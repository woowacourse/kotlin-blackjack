package blackjack.domain

import blackjack.domain.Participant.Companion.BLACKJACK_BUST_LIMIT

data class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> = cards.toList()

    fun getScore(): Int {
        return cards.sumOf { it.getScore() }
    }

    fun countAce(): Int {
        return cards.count { it.rank == Rank.ACE }
    }

    fun size(): Int {
        return this.size()
    }

    fun calculateTotalSum(): Int {
        var score = getScore()
        var aceCount = countAce()
        while (score > BLACKJACK_BUST_LIMIT && aceCount > 0) {
            score -= ACE_SCORE_DIFFERENCE
            aceCount--
        }

        return score
    }

    companion object {
        const val BLACKJACK_BUST_LIMIT = 21
        private const val ACE_HIGH = 11
        private const val ACE_LOW = 1
        private const val ACE_SCORE_DIFFERENCE = ACE_HIGH - ACE_LOW
    }
}
