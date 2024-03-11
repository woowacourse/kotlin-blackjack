package blackjack.model.card

class Hand(
    val cards: List<Card>,
    private val pointCalculator: PointCalculator = DefaultPointCalculator(BLACKJACK_NUMBER),
) {
    init {
        require(cards.size >= MIN_HAND_CARDS_SIZE) { "손패는 $MIN_HAND_CARDS_SIZE 장 이상임" }
    }

    constructor(vararg cards: Card) : this(cards.toList())

    operator fun plus(card: Card): Hand = Hand(cards = cards + card)

    fun sumOptimized(): Int = pointCalculator.sumOf(cards)

    fun isBust(): Boolean = sumOptimized() > BLACKJACK_NUMBER

    fun isBlackjack(): Boolean = (sumOptimized() == BLACKJACK_NUMBER) && (cards.size == MIN_HAND_CARDS_SIZE)

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val MIN_HAND_CARDS_SIZE = 2
    }
}
