package blackjack.model

class HandCard {
    private var cards: Set<Card> = setOf()

    fun addCard(card: Card) {
        cards += card
    }

    fun getCards(): Set<Card> {
        return cards
    }

    fun getTotalCardsSum(): Int {
        return cards.sumOf { card ->
            card.getCardDenomination().getScore()
        }
    }
}
