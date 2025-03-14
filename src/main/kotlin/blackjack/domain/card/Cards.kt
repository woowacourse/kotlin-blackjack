package blackjack.domain.card

class Cards {
    private val cards = mutableListOf<Card>()

    fun add(card: Card) {
        cards.add(card)
    }

    fun toList(): List<Card> = cards.toList()

    fun countAce(): Int {
        return cards.count { it.rank == Rank.ACE }
    }

    fun countScoredTen(): Int {
        val highRanks = setOf(Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING)
        return cards.count { it.rank in highRanks }
    }

    fun size(): Int {
        return cards.size
    }

    companion object {
        private const val ACE_SPECIFIC_SCORE = 11
    }
}
