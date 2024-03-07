package blackjack.model

class HandCards(
    val cards: List<Card>,
    private val pointCalculator: PointCalculator = DefaultPointCalculator(),
) {
    init {
        require(cards.size >= 2) { "손패는 2장 이상임" }
    }

    constructor(vararg cards: Card) : this(cards.toList())

    fun sumOptimizedOrNull(): Int? = pointCalculator.sumOrNull(cards)

    fun sumOptimized(): Int {
        val sum = sumOptimizedOrNull()
        return sum ?: throw IllegalArgumentException("$sum 은 21 이하여야함")
    }

    fun isBust(): Boolean = sumOptimizedOrNull() == null

    fun isBlackjack(): Boolean = (sumOptimizedOrNull() == 21) && (cards.size == 2)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HandCards

        return cards == other.cards
    }

    override fun hashCode(): Int {
        return cards.hashCode()
    }
}
