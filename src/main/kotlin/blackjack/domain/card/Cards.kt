package blackjack.domain.card

class Cards {
    private val cards = mutableListOf<Card>()

    fun add(card: Card) {
        cards.add(card)
    }

    fun toList(): List<Card> = cards.toList()

    private fun getScore(): Int {
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

    fun countScoredTen(): Int {
        val highRanks = setOf(Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING)
        return cards.count { it.rank in highRanks }
    }

    fun size(): Int {
        return cards.size
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
