package view

import entity.CardNumber
import entity.CardType
import entity.Cards

class ViewUtils {
    companion object {
        private fun CardNumber.toShorthandString(): String {
            return when (this) {
                CardNumber.ACE -> "A"
                CardNumber.KING -> "K"
                CardNumber.QUEEN -> "Q"
                CardNumber.JACK -> "J"
                else -> (ordinal + 1).toString()
            }
        }

        private fun CardType.toKoreanString(): String {
            return when (this) {
                CardType.CLUB -> "클로버"
                CardType.HEART -> "하트"
                CardType.SPADE -> "스페이드"
                CardType.DIAMOND -> "다이아몬드"
            }
        }

        fun cardsToString(cards: Cards): String {
            return cards.value.joinToString(", ") {
                it.cardNumber.toShorthandString() + it.cardType.toKoreanString()
            }
        }
    }
}
