package view

import entity.CardNumber
import entity.CardType
import entity.Cards

class ViewUtils {
    companion object {
        fun cardNumberToString(cardNumber: CardNumber): String {
            if (cardNumber.ordinal in (1..9)) return (cardNumber.ordinal + 1).toString()
            else if (cardNumber == CardNumber.ACE) return "A"
            else if (cardNumber == CardNumber.KING) return "K"
            else if (cardNumber == CardNumber.QUEEN) return "Q"
            else if (cardNumber == CardNumber.JACK) return "J"
            return ""
        }

        fun cardTypeToString(cardType: CardType): String {
            if (cardType == CardType.CLUB) return "클로버"
            else if (cardType == CardType.HEART) return "하트"
            else if (cardType == CardType.SPADE) return "스페이드"
            else if (cardType == CardType.DIAMOND) return "다이아몬드"
            return ""
        }

        fun cardsToString(cards: Cards): String {
            return cards.value.joinToString(", ") {
                cardNumberToString(it.cardNumber) + cardTypeToString(it.cardType)
            }
        }
    }
}
