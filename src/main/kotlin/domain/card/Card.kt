package domain.card

class Card private constructor(val cardCategory: CardCategory, val cardNumber: CardNumber) {
    val isAce: Boolean
        get() = cardNumber == CardNumber.ACE

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Card) return false
        return cardCategory == other.cardCategory && cardNumber == other.cardNumber
    }

    override fun hashCode(): Int {
        return cardCategory.hashCode() + cardNumber.hashCode()
    }

    companion object {
        val DECK: List<Card> =
            CardCategory.values().flatMap { cardCategory -> CardNumber.values().map { Card(cardCategory, it) } }

        fun of(cardCategory: CardCategory, cardNumber: CardNumber): Card {
            return DECK.find { cardCategory == it.cardCategory && cardNumber == it.cardNumber }
                ?: Card(cardCategory, cardNumber)
        }
    }
}
