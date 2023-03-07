package domain

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
        private val CARDS: List<Card> =
            CardCategory.values().flatMap { cardCategory -> CardNumber.values().map { Card(cardCategory, it) } }

        fun of(cardCategory: CardCategory, cardNumber: CardNumber): Card {
            val findCard = CARDS.find { cardCategory == it.cardCategory && cardNumber == it.cardNumber }
            requireNotNull(findCard) { "" }
            return findCard
        }
    }
}
