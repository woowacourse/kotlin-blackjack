package view

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape

object ViewCommon {
    fun cardToString(card: Card): String {
        return cardNumberToString(card.number) + cardShapeToString(card.shape)
    }

    private fun cardNumberToString(cardNumber: CardNumber): String {
        return when (cardNumber) {
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
            CardNumber.KING -> "K"
            CardNumber.QUEEN -> "Q"
            CardNumber.JACK -> "J"
        }
    }

    private fun cardShapeToString(cardShape: CardShape): String {
        return when (cardShape) {
            CardShape.HEART -> "하트"
            CardShape.CLOVER -> "클로버"
            CardShape.DIAMOND -> "다이아몬드"
            CardShape.SPADE -> "스페이드"
        }
    }
}
