package blackjack.domain.model.card

enum class Suit {
    SPADE,
    HART,
    DIAMOND,
    CLOVER,
    ;

    companion object {
        fun getBySuitIndex(suitIndex: Int): Suit = entries.getOrNull(suitIndex) ?: throw IllegalArgumentException(ERROR_UNKNOWN_SUIT_INDEX)

        private val ERROR_UNKNOWN_SUIT_INDEX = "알 수 없는 카드 문양 인덱스입니다."
    }
}
