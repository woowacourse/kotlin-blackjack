package blackjack.domain.model.card

import blackjack.domain.model.card.CardNumber.Companion.MAX_ORDER_NUMBER

data class Card(
    val cardNumber: CardNumber,
    val suit: Suit = Suit.SPADE,
) {
    constructor(cardIndex: Int) : this(
        cardNumber = CardNumber.getByOrderNumber((cardIndex) % MAX_ORDER_NUMBER + 1),
        suit = Suit.getBySuitIndex((cardIndex) / MAX_ORDER_NUMBER),
    ) {
        require(cardIndex in CARD_INDEX_RANGE) { ERROR_OUT_OF_CARD_INDEX }
    }

    val minimumValue: Int
        get() = cardNumber.value.first()

    val maximumValue: Int
        get() = cardNumber.value.last()

    companion object {
        private const val MIN_CARD_INDEX = 0
        private val MAX_CARD_INDEX = (MAX_ORDER_NUMBER * (Suit.entries.size)) - 1
        val CARD_INDEX_RANGE = MIN_CARD_INDEX..MAX_CARD_INDEX

        private val ERROR_OUT_OF_CARD_INDEX = "카드 인덱스는 $CARD_INDEX_RANGE 에 속하는 값이어야 합니다"
    }
}
