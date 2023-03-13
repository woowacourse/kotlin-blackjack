package blackjack.domain.card

class Cards(val items: List<Card> = listOf()) {
    constructor(vararg cards: Card) : this(cards.toList())

    val isBust: Boolean
        get() = calculateTotalScore() > BLACKJACK_SCORE
    val isBlackjack: Boolean
        get() = calculateTotalScore() == BLACKJACK_SCORE

    fun add(card: Card): Cards = Cards((items + card).toMutableList())

    fun isGreaterOrEqualsCardSize(size: Int): Boolean = items.size >= size

    fun getFirstCard(): Card = items.first()

    fun calculateTotalScore(): Int {
        val score = items.sumOf(Card::getScore)
        return calculateAceScore(score)
    }

    private fun calculateAceScore(score: Int): Int =
        if (hasAce() && !isOverBlackjack(score + BONUS_SCORE)) score + BONUS_SCORE else score

    private fun isOverBlackjack(score: Int): Boolean = score > BLACKJACK_SCORE

    private fun hasAce(): Boolean = items.any(Card::isAce)

    companion object {
        private const val BONUS_SCORE = 10
        private const val BLACKJACK_SCORE = 21
    }
}
