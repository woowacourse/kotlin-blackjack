package blackjack.domain

class CardHand(cards: List<Card>) {
    private var _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    val size: Int
        get() = cards.size

    init {
        require(cards.size == INITIAL_CARDS_SIZE)
    }

    fun draw(card: Card) {
        _cards.add(card)
        CardNumber.values()
    }

    fun getMinimumCardsScore(): Int = cards.sumOf { card -> card.number.value }

    fun getTotalCardsScore(): Int {
        val aceCardsCount = cards.count { card -> card.number == CardNumber.A }
        var currentSum = cards.filter { card -> card.number != CardNumber.A }
            .sumOf { card -> card.number.value }

        repeat(aceCardsCount) {
            currentSum += decideAceCardsScore(currentSum)
        }
        return currentSum
    }

    private fun decideAceCardsScore(currentSum: Int): Int {
        if (currentSum >= CURRENT_SUM_STANDARD) {
            return SMALL_ACE_VALUE
        }

        return BIG_ACE_VALUE
    }

    companion object {
        const val INITIAL_CARDS_SIZE = 2
        private const val SMALL_ACE_VALUE = 1
        private const val BIG_ACE_VALUE = 11
        private const val CURRENT_SUM_STANDARD = 11
    }
}
