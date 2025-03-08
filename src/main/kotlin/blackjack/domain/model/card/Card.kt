package blackjack.domain.model.card

data class Card(
    val cardNumber: CardNumber,
    val suit: Suit,
) {
    init {
        require(cardNumber.value in MINIMUM_CARD_NUMBER..MAXIMUM_CARD_NUMBER) { ERROR_OUT_OF_CARD_NUMBER }
        require(suit in Suit.entries) { ERROR_INVALID_SUIT }
    }

    fun isAce(): Boolean {
        return cardNumber == CardNumber.ACE
    }

    companion object {
        private const val MINIMUM_CARD_NUMBER = 1
        private const val MAXIMUM_CARD_NUMBER = 13

        private const val ERROR_OUT_OF_CARD_NUMBER = "카드 번호는 ${MINIMUM_CARD_NUMBER}에서 $MAXIMUM_CARD_NUMBER 사이여야 합니다."
        private const val ERROR_INVALID_SUIT = "유효하지 않은 카드 문양입니다."
    }
}
