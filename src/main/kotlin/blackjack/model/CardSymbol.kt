package blackjack.model

enum class CardSymbol {
    DIAMOND,
    HEART,
    SPADE,
    CLOVER, ;

    companion object {
        fun convertCardSymbol(cardSymbol: CardSymbol): String {
            return when (cardSymbol) {
                DIAMOND -> "다이아몬드"
                HEART -> "하트"
                SPADE -> "스페이드"
                CLOVER -> "클로버"
            }
        }
    }
}
