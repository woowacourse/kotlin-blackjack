package blackjack.domain

class Cards {
    private val _items: MutableList<Card> by lazy { mutableListOf() }
    val items: List<Card>
        get() = _items.toList()

    fun add(card: Card) {
        _items.add(card)
    }

    fun getFirstCard(): Card = _items.first()

    fun calculateTotalScore(): Int {
        val score = _items.sumOf(Card::getScore)
        return calculateAceScore(score)
    }

    private fun calculateAceScore(score: Int): Int =
        if (hasAce() && !isOverBlackjack(score + BONUS_SCORE)) score + BONUS_SCORE else score

    fun isOverBlackjack(score: Int): Boolean = score > BLACKJACK_SCORE

    fun isStay(): Boolean = calculateTotalScore() >= STAY_SCORE

    private fun hasAce(): Boolean = _items.any(Card::isAce)

    companion object {
        private const val BONUS_SCORE = 10
        private const val BLACKJACK_SCORE = 21
        private const val STAY_SCORE = 17
    }
}
