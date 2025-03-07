package blackjack.domain.model

import blackjack.domain.model.CardNumber.Companion.MAX_CARD_NUMBER
import blackjack.domain.model.Suit.Companion.MAX_SUIT_NUMBER

data class Card(
    val cardNumber: CardNumber,
    private val suit: Suit,
) {
    constructor(cardIndex: Int) : this(
        cardNumber = CardNumber((cardIndex) % MAX_CARD_NUMBER + 1),
        suit = Suit((cardIndex) / MAX_CARD_NUMBER),
    ) {
        require(cardIndex in CARD_INDEX_RANGE) { ERROR_OUT_OF_CARD_INDEX }
    }

    fun getCardText(): String = cardNumber.getCardNumberName() + suit.getSuitName()

    companion object {
        private const val MIN_CARD_INDEX = 0
        private const val MAX_CARD_INDEX = (MAX_CARD_NUMBER * (MAX_SUIT_NUMBER + 1)) - 1
        val CARD_INDEX_RANGE = MIN_CARD_INDEX..MAX_CARD_INDEX

        private val ERROR_OUT_OF_CARD_INDEX = "카드 인덱스는 $CARD_INDEX_RANGE 에 속하는 값이어야 합니다"
    }
}
