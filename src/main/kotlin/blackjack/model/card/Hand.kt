package blackjack.model.card

@JvmInline
value class Hand(
    val cards: List<Card>,
) {
    init {
        require(cards.size >= MIN_HAND_CARDS_SIZE) { "손패는 $MIN_HAND_CARDS_SIZE 장 이상임" }
    }

    constructor(vararg cards: Card) : this(cards.toList())

    operator fun plus(card: Card): Hand = Hand(cards = cards + card)

    fun sum(): Int = sumWith(DefaultSumPointStrategy)

    fun sumWith(sumPointStrategy: SumPointStrategy) = sumPointStrategy.sumOf(cards)

    fun isBust(): Boolean = sum() > BLACKJACK_NUMBER

    fun isBlackjack(): Boolean = (sum() == BLACKJACK_NUMBER) && (cards.size == MIN_HAND_CARDS_SIZE)

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val MIN_HAND_CARDS_SIZE = 2
        private val DefaultSumPointStrategy = OptimalSumPointStrategy(BLACKJACK_NUMBER)
    }
}
