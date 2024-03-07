package blackjack.model

class HandCards(private val deck: Deck) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.toList()

    init {
        _cards.addAll(deck.draw(INIT_CARD_AMOUNT))
    }

    fun add() {
        _cards.addAll(deck.draw(HIT_CARD_AMOUNT))
    }

    fun calculateCardScore(): Int {
        val baseScore = cards.sumOf { it.cardNumber.score }
        val hasAce = cards.any { it.cardNumber == CardNumber.ACE }
        return if (hasAce && baseScore + ANOTHER_CARD_SCORE <= BLACKJACK_NUMBER) baseScore + ANOTHER_CARD_SCORE else baseScore
    }

    companion object {
        private const val INIT_CARD_AMOUNT = 2
        private const val HIT_CARD_AMOUNT = 1
        private const val ANOTHER_CARD_SCORE = 10
        private const val BLACKJACK_NUMBER = 21
    }
}
