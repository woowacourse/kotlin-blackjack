package blackjack.model

class HandCards(
    val cards: List<Card>,
    private val pointCalculator: PointCalculator = DefaultPointCalculator(),
) {
    init {
        require(cards.size >= 2) { "손패는 2장 이상임" }
    }

    constructor(vararg cards: Card) : this(cards.toList())

    fun sumOrNull(): Int? = pointCalculator.sumOrNull(cards)

    fun isBust(): Boolean = sumOrNull() == null

    fun isBlackjack(): Boolean = (sumOrNull() == 21) && (cards.size == 2)

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
