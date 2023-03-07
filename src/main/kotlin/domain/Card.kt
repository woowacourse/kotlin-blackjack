package domain

class Card private constructor(val cardCategory: CardCategory, val cardNumber: CardNumber) {
    val isAce: Boolean
        get() = cardNumber == CardNumber.ACE

    companion object {
        private const val NO_CARD_EXCEPTION = "[ERROR] 존재하지 않는 카드입니다!!!"
        private val cardDeck =
            CardCategory.values().flatMap { cardCategory -> CardNumber.values().map { Card(cardCategory, it) } }

        fun of(cardCategory: CardCategory, cardNumber: CardNumber): Card {
            return cardDeck.find { it.cardCategory == cardCategory && it.cardNumber == cardNumber }
                ?: throw IllegalStateException(NO_CARD_EXCEPTION)
        }
    }
}
