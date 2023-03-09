package view

import domain.card.CardNumber
import domain.card.CardNumber.ACE
import domain.card.CardNumber.EIGHT
import domain.card.CardNumber.FIVE
import domain.card.CardNumber.FOUR
import domain.card.CardNumber.JACK
import domain.card.CardNumber.KING
import domain.card.CardNumber.NINE
import domain.card.CardNumber.QUEEN
import domain.card.CardNumber.SEVEN
import domain.card.CardNumber.SIX
import domain.card.CardNumber.TEN
import domain.card.CardNumber.THREE
import domain.card.CardNumber.TWO
import domain.card.CardShape

object ViewUtils {
    fun cardShapeToText(cardShape: CardShape) = when (cardShape) {
        CardShape.HEART -> "하트"
        CardShape.CLOVER -> "클로버"
        CardShape.SPADE -> "스페이드"
        CardShape.DIAMOND -> "다이아몬드"
    }

    fun cardNumberToText(cardNumber: CardNumber) = when (cardNumber) {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN -> cardNumber.value.toString()
        ACE -> "A"
        KING -> "K"
        QUEEN -> "Q"
        JACK -> "J"
    }
}
