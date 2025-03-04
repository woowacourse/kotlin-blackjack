package blackjack

class Card(
    private val cardNumber: Int,
    private val suit: Int,
) {
    init {
        require(cardNumber in CARD_NUMBER_RANGE) { ERROR_OUT_OF_CARD_NUMBER_RANGE }
        require(suit in SUIT_RANGE) { ERROR_OUT_OF_SUIT_RANGE }
    }

    companion object {
        private const val MIN_CARD_NUMBER = 1
        private const val MAX_CARD_NUMBER = 13
        private val CARD_NUMBER_RANGE = MIN_CARD_NUMBER..MAX_CARD_NUMBER

        private const val MIN_SUIT_NUMBER = 0
        private const val MAX_SUIT_NUMBER = 3
        private val SUIT_RANGE = MIN_SUIT_NUMBER..MAX_SUIT_NUMBER

        private val ERROR_OUT_OF_CARD_NUMBER_RANGE = "카드 숫자는 $CARD_NUMBER_RANGE 에 속하는 값이여야 합니다"
        private val ERROR_OUT_OF_SUIT_RANGE = "카드 문양은 $SUIT_RANGE 에 속하는 값이여야 합니다"
    }
}
