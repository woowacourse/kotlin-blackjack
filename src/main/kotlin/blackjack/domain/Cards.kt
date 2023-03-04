package blackjack.domain

class Cards(
    cards: List<Card> = listOf(Card.draw(), Card.draw())
) {

    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = cards.size

    init {
        require(cards.size == INITIAL_CARDS_SIZE)
    }

    fun draw(card: Card = Card.draw()) {
        _cards.add(card)
        CardNumber.values()
    }

    fun getMinimumCardsScore(): Int = cards.sumOf { card -> card.number.value }

    fun getTotalCardsScore(): Int {
        val aceCardsCount = cards.count { card -> card.number == CardNumber.SMALL_A }
        var currentSum = cards
            .filter { card -> card.number != CardNumber.SMALL_A }
            .sumOf { card -> card.number.value }

        repeat(aceCardsCount) {
            currentSum += decideAceCardsScore(currentSum)
        }
        return currentSum
    }

    private fun decideAceCardsScore(currentSum: Int): Int {
        if (currentSum >= CURRENT_SUM_STANDARD) {
            return CardNumber.SMALL_A.value
        }

        return CardNumber.BIG_A.value
    }

    companion object {
        const val INITIAL_CARDS_SIZE = 2
        private const val CURRENT_SUM_STANDARD = 11
    }
}
