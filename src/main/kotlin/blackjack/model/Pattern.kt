package blackjack.model

enum class Pattern {
    HEART, SPADE, DIAMOND, CLOVER;
}

fun Pattern.display(): String {
    return when (this) {
        Pattern.HEART -> "하트"
        Pattern.SPADE -> "스페이드"
        Pattern.DIAMOND -> "다이아몬드"
        Pattern.CLOVER -> "클로버"
    }
}
