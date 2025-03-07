package blackjack.domain

enum class Suit() {
    SPADE,
    HEART,
    DIAMOND,
    CLUB,
}

fun Suit.toDisplayName(): String {
    return when (this) {
        Suit.SPADE -> "스페이드"
        Suit.HEART -> "하트"
        Suit.DIAMOND -> "다이아몬드"
        Suit.CLUB -> "클로버"
    }
}
