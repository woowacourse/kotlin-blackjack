package blackjack.model

class Cards {
    private val cards: MutableList<Card> = mutableListOf()

    fun isBusted(): Boolean {
        val score = sum()
        val threshold = BLACKJACK_THRESHOLD
        return threshold < score
    }

    fun isMaxScore(): Boolean {
        val score = sum()
        val threshold = BLACKJACK_THRESHOLD
        return threshold == score
    }

    fun isBlackJack(): Boolean = cards.size == 2 && isMaxScore()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun showCard(): List<Card> = cards.toList()

    fun sum(): Int {
        val sum = cards.sumOf { it.cardNumber.score }
        val additionalScore = if (hasAce() && sum <= 11) 10 else 0
        return sum + additionalScore
    }

    private fun hasAce() = cards.any { it.cardNumber == CardNumber.Ace }
    companion object {
        private const val BLACKJACK_THRESHOLD = 21
    }
}
