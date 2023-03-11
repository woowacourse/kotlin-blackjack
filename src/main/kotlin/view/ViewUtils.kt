package view

import entity.card.CardNumber
import entity.card.CardType
import entity.card.Cards

class ViewUtils {
    companion object {
        private fun CardNumber.isString(): String {
            return when (this) {
                CardNumber.ACE -> "A"
                CardNumber.KING -> "K"
                CardNumber.QUEEN -> "Q"
                CardNumber.JACK -> "J"
                else -> (ordinal + 1).toString()
            }
        }

        private fun CardType.isString(): String {
            return when (this) {
                CardType.CLUB -> "클로버"
                CardType.SPADE -> "하트"
                CardType.HEART -> "스페이드"
                CardType.DIAMOND -> "다이아몬드"
            }
        }

        fun Cards.isString(): String {
            return value.joinToString(", ") {
                it.cardNumber.isString() + it.cardType.isString()
            }
        }
    }
}
