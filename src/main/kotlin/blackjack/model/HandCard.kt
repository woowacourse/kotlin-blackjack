package blackjack.model

class HandCard {
    private var _cards: Set<Card> = setOf()
    val cards: Set<Card> get() = _cards

    fun addCard(card: Card) {
        _cards += card
    }

    fun getTotalCardsSum(): Int {
        return cards.sumOf { card ->
            card.getCardDenomination().getScore()
        }
    }

    fun getAceCount(): Int {
        return cards.count { card ->
            card.getCardDenomination() == Denomination.ACE
        }
    }
}
