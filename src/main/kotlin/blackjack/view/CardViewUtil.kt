package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.CardMark
import blackjack.domain.card.CardValue

fun Card.toText(): String = "${value.pattern()}${mark.name()}"

private fun CardMark.name(): String =
    when (this) {
        CardMark.CLOVER -> "클로버"
        CardMark.DIA -> "다이아몬드"
        CardMark.HEART -> "하트"
        CardMark.SPADE -> "스페이드"
    }

private fun CardValue.pattern(): String =
    when (this) {
        CardValue.ACE -> "A"
        CardValue.KING -> "K"
        CardValue.QUEEN -> "Q"
        CardValue.JACK -> "J"
        CardValue.TEN -> "10"
        CardValue.NINE -> "9"
        CardValue.EIGHT -> "8"
        CardValue.SEVEN -> "7"
        CardValue.SIX -> "6"
        CardValue.FIVE -> "5"
        CardValue.FOUR -> "4"
        CardValue.THREE -> "3"
        CardValue.TWO -> "2"
    }
