package blackjack.util

import blackjack.model.CardNumber
import blackjack.model.Pattern

fun CardNumber.display(): String {
    return when (this) {
        CardNumber.ACE -> "A"
        CardNumber.TWO -> "2"
        CardNumber.THREE -> "3"
        CardNumber.FOUR -> "4"
        CardNumber.FIVE -> "5"
        CardNumber.SIX -> "6"
        CardNumber.SEVEN -> "7"
        CardNumber.EIGHT -> "8"
        CardNumber.NINE -> "9"
        CardNumber.TEN -> "10"
        CardNumber.JACK -> "J"
        CardNumber.QUEEN -> "Q"
        CardNumber.KING -> "K"
    }
}

fun Pattern.display(): String {
    return when (this) {
        Pattern.HEART -> "하트"
        Pattern.SPADE -> "스페이드"
        Pattern.DIAMOND -> "다이아몬드"
        Pattern.CLOVER -> "클로버"
    }
}
