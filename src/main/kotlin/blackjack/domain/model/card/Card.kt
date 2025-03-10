package blackjack.domain.model.card

import blackjack.domain.model.card.Number.Companion.MAX_ORDER_NUMBER
import blackjack.domain.model.card.Suit.Companion.MAX_SUIT_NUMBER

data class Card(
    val number: Number,
    val suit: Suit,
) {
    constructor(cardIndex: Int) : this(
        number = Number.getByOrderNumber((cardIndex) % MAX_ORDER_NUMBER + 1),
        suit = Suit((cardIndex) / MAX_ORDER_NUMBER),
    ) {
        require(cardIndex in CARD_INDEX_RANGE) { ERROR_OUT_OF_CARD_INDEX }
    }

    fun getCardText(): String = number.initial + suit.getSuitName()

    fun getMinimumValue(): Int = number.value.first()

    fun getMaximumValue(): Int = number.value.last()

    companion object {
        private const val MIN_CARD_INDEX = 0
        private const val MAX_CARD_INDEX = (MAX_ORDER_NUMBER * (MAX_SUIT_NUMBER + 1)) - 1
        val CARD_INDEX_RANGE = MIN_CARD_INDEX..MAX_CARD_INDEX

        private val ERROR_OUT_OF_CARD_INDEX = "카드 인덱스는 $CARD_INDEX_RANGE 에 속하는 값이어야 합니다"
    }
}
