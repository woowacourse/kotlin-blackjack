package blackjack.model.deck

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
        val adjustedScore = baseScore + ACE_EXTRA_SCORE
        return if (hasAce && adjustedScore <= BLACKJACK_NUMBER) adjustedScore else baseScore
    }

    fun isBlackjackCard(): Boolean = cards.size == 2 && calculateCardScore() == BLACKJACK_NUMBER

    companion object {
        private const val INIT_CARD_AMOUNT = 2
        private const val HIT_CARD_AMOUNT = 1
        private const val ACE_EXTRA_SCORE = 10
        private const val BLACKJACK_NUMBER = 21
    }
}
