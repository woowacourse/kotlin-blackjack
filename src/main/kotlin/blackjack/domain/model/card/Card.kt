package blackjack.domain.model.card

data class Card private constructor(
    val cardNumber: CardNumber,
    val suit: Suit,
) {
    init {
        require(cardNumber in CardNumber.entries) { ERROR_OUT_OF_CARD_NUMBER }
        require(suit in Suit.entries) { ERROR_INVALID_SUIT }
    }

    fun isAce(): Boolean {
        return cardNumber == CardNumber.ACE
    }

    companion object {
        val standardCards: Map<Pair<CardNumber, Suit>, Card> =
            Suit.entries.flatMap { suit -> CardNumber.entries.map { cardNumber -> Card(cardNumber, suit) } }
                .associateBy { it.cardNumber to it.suit }

        fun of(
            cardNumber: CardNumber,
            suit: Suit,
        ): Card {
            return standardCards[cardNumber to suit] ?: throw IllegalArgumentException(INVALID_CARD_QUERY)
        }

        private const val MINIMUM_CARD_NUMBER = 1
        private const val MAXIMUM_CARD_NUMBER = 13

        private const val ERROR_OUT_OF_CARD_NUMBER = "카드 번호는 ${MINIMUM_CARD_NUMBER}에서 $MAXIMUM_CARD_NUMBER 사이여야 합니다."
        private const val ERROR_INVALID_SUIT = "유효하지 않은 카드 문양입니다."
        private const val INVALID_CARD_QUERY = "유효하지 않은 숫자 또는 문양으로 카드를 생성하려 했습니다."
    }
}
