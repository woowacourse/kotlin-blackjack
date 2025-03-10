package view

object ViewMapper {
    fun String.mapToTitle(): String {
        return when (this) {
            "ACE" -> "A"
            "KING" -> "K"
            "QUEEN" -> "Q"
            "JACK" -> "J"
            "TWO" -> "2"
            "THREE" -> "3"
            "FOUR" -> "4"
            "FIVE" -> "5"
            "SIX" -> "6"
            "SEVEN" -> "7"
            "EIGHT" -> "8"
            "NINE" -> "9"
            "TEN" -> "10"
            "HEART" -> "하트"
            "SPADE" -> "스페이드"
            "CLUB" -> "클로버"
            "DIAMOND" -> "다이아몬드"
            else -> throw IllegalArgumentException("[ERROR] 존재하지 않는 카드입니다.")
        }
    }
}