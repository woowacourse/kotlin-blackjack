package blackjack.domain

class Hand {
    private val cards: MutableList<Card> = mutableListOf()
    private var totalSum: Int = 0

    fun getCards(): List<Card> = cards.toList()

    fun getTotalSum(): Int = totalSum

    fun addCard(card: Card) {
        cards.add(card)
        updateTotalSum()
    }

    fun isBust(): Boolean {
        return totalSum > BUST_THRESHOLD
    }

    fun isBlackJack(): Boolean {
        return hasAce() && hasTenRankCard()
    }

    private fun updateTotalSum() {
        var sum = cards.sumOf { it.getScore() }

        if (hasAce() && sum + ACE_BONUS_SCORE <= BUST_THRESHOLD) {
            sum += ACE_BONUS_SCORE
        }

        totalSum = sum
    }

    private fun hasAce(): Boolean {
        return cards.any { it.rank == Rank.ACE }
    }

    private fun hasTenRankCard(): Boolean {
        val tenRankCards = listOf(Rank.TEN, Rank.JACK, Rank.QUEEN, Rank.KING)
        return cards.any { it.rank in tenRankCards }
    }

    companion object {
        const val BUST_THRESHOLD = 21
        private const val ACE_BONUS_SCORE = 10
    }
}
