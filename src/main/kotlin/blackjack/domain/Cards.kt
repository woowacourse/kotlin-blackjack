package blackjack.domain

data class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> = cards.toList()

    fun getScore(): Int {
        return cards.sumOf {
            if (it.rank == Rank.ACE) {
                ACE_SPECIFIC_SCORE
            } else {
                it.getScore()
            }
        }
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
            score -= ACE_SPECIFIC_SCORE - Rank.ACE.score
            aceCount--
        }

        return score
    }

    companion object {
        const val BLACKJACK_BUST_LIMIT = 21
        private const val ACE_SPECIFIC_SCORE = 11
    }
}
