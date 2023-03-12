package blackjack.domain.card

class Cards(private val _items: MutableList<Card> = mutableListOf()) {
    constructor(vararg cards: Card) : this(cards.toMutableList())

    val items: List<Card>
        get() = _items.toList()
    val isBust: Boolean
        get() = calculateTotalScore() > BLACKJACK_SCORE
    val isBlackjack: Boolean
        get() = calculateTotalScore() == BLACKJACK_SCORE

    fun add(card: Card): Cards = Cards((_items + card).toMutableList())

    fun isGreaterOrEqualsCardSize(size: Int): Boolean = _items.size >= size

    fun getFirstCard(): Card = _items.first()

    fun calculateTotalScore(): Int {
        val score = _items.sumOf(Card::getScore)
        return calculateAceScore(score)
    }

    private fun calculateAceScore(score: Int): Int =
        if (hasAce() && !isOverBlackjack(score + BONUS_SCORE)) score + BONUS_SCORE else score

    private fun isOverBlackjack(score: Int): Boolean = score > BLACKJACK_SCORE

    private fun hasAce(): Boolean = _items.any(Card::isAce)

    companion object {
        private const val BONUS_SCORE = 10
        private const val BLACKJACK_SCORE = 21
    }
}
