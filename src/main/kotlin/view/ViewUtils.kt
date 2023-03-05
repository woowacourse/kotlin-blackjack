package view

import entity.CardNumber
import entity.CardType
import entity.Cards

class ViewUtils {
    companion object {
        fun cardNumberToString(cardNumber: CardNumber): String {
            return when (cardNumber) {
                CardNumber.ACE -> "A"
                CardNumber.KING -> "K"
                CardNumber.QUEEN -> "Q"
                CardNumber.JACK -> "J"
                else -> (cardNumber.ordinal + 1).toString()
            }
        }

        fun cardTypeToString(cardType: CardType): String {
            return when (cardType) {
                CardType.CLUB -> "클로버"
                CardType.SPADE -> "하트"
                CardType.HEART -> "스페이드"
                CardType.DIAMOND -> "다이아몬드"
            }
        }

        fun cardsToString(cards: Cards): String {
            return cards.value.joinToString(", ") {
                cardNumberToString(it.cardNumber) + cardTypeToString(it.cardType)
            }
        }
    }
}
