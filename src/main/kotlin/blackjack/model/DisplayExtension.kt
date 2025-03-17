package blackjack.model

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardNumber.ACE
import blackjack.model.card.CardNumber.EIGHT
import blackjack.model.card.CardNumber.FIVE
import blackjack.model.card.CardNumber.FOUR
import blackjack.model.card.CardNumber.JACK
import blackjack.model.card.CardNumber.KING
import blackjack.model.card.CardNumber.NINE
import blackjack.model.card.CardNumber.QUEEN
import blackjack.model.card.CardNumber.SEVEN
import blackjack.model.card.CardNumber.SIX
import blackjack.model.card.CardNumber.TEN
import blackjack.model.card.CardNumber.THREE
import blackjack.model.card.CardNumber.TWO
import blackjack.model.card.Shape
import blackjack.model.card.Shape.CLOVER
import blackjack.model.card.Shape.DIAMOND
import blackjack.model.card.Shape.HEART
import blackjack.model.card.Shape.SPADE

fun Card.toUi(): String = "${this.number.toUi()}${this.shape.toUi()}"

fun CardNumber.toUi(): String =
    when (this) {
        ACE -> "A"
        TWO -> "2"
        THREE -> "3"
        FOUR -> "4"
        FIVE -> "5"
        SIX -> "6"
        SEVEN -> "7"
        EIGHT -> "8"
        NINE -> "9"
        TEN -> "10"
        JACK -> "J"
        QUEEN -> "Q"
        KING -> "K"
    }

fun Shape.toUi(): String =
    when (this) {
        SPADE -> "스페이드"
        DIAMOND -> "다이아몬드"
        HEART -> "하트"
        CLOVER -> "클로버"
    }
