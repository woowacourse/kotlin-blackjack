package blackjack.model.deck

class HandCards {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.toList()

    fun add(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun calculateCardScore(): Int {
        val baseScore = cards.sumOf { it.cardNumber.score }
        val hasAce = cards.any { it.cardNumber == CardNumber.ACE }
        return if (hasAce && baseScore + ANOTHER_CARD_SCORE <= BLACKJACK_NUMBER) baseScore + ANOTHER_CARD_SCORE else baseScore
    }

    fun isBlackjackCard(): Boolean = cards.size == 2 && calculateCardScore() == BLACKJACK_NUMBER

    companion object {
        private const val ANOTHER_CARD_SCORE = 10
        private const val BLACKJACK_NUMBER = 21
    }
}
