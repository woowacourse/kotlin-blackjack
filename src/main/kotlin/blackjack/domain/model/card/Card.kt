package blackjack.domain.model.card

import blackjack.domain.model.card.Number.Companion.MAX_ORDER_NUMBER

data class Card(
    val number: Number,
    val suit: Suit,
) {
    constructor(cardIndex: Int) : this(
        number = Number.getByOrderNumber((cardIndex) % MAX_ORDER_NUMBER + 1),
        suit = Suit.getBySuitIndex((cardIndex) / MAX_ORDER_NUMBER),
    ) {
        require(cardIndex in CARD_INDEX_RANGE) { ERROR_OUT_OF_CARD_INDEX }
    }

    // TODO("카드 한글 이름 출력을 Output View로 역할 위임 예정")
    fun getCardText(): String = number.initial + getSuitName()

    // TODO("카드 문양 이름 출력을 Output View로 역할 위임 예정")
    fun getSuitName(): String =
        when (suit) {
            Suit.SPADE -> "스페이드"
            Suit.HART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLOVER -> "클로버"
        }

    fun getMinimumValue(): Int = number.value.first()

    fun getMaximumValue(): Int = number.value.last()

    companion object {
        private const val MIN_CARD_INDEX = 0
        private val MAX_CARD_INDEX = (MAX_ORDER_NUMBER * (Suit.entries.size)) - 1
        val CARD_INDEX_RANGE = MIN_CARD_INDEX..MAX_CARD_INDEX

        private val ERROR_OUT_OF_CARD_INDEX = "카드 인덱스는 $CARD_INDEX_RANGE 에 속하는 값이어야 합니다"
    }
}
