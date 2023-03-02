package view

import entity.CardNumber
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

        fun cardsToString(cards: Cards): String {
            return cards.value.joinToString(", ") {
                it.cardNumber.toString()
            }
        }
    }
}
