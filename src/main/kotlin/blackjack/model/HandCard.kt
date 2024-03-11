package blackjack.model

class HandCard {
    private var _cards: Set<Card> = setOf()
    val cards: Set<Card> get() = _cards

    fun addCard(card: Card) {
        _cards += card
    }

    fun getTotalCardsSum(): Int {
        var aceCount = getAceCount()

        var totalScore =
            cards.sumOf { card ->
                card.getCardDenomination().getScore()
            }

        while (aceCount > 0 && totalScore > BlackJack.BUST_SCORE) {
            totalScore -= Denomination.ACE.getScore() - Denomination.aceTransferScore()
            aceCount--
        }

        return totalScore
    }

    fun getAceCount(): Int {
        return cards.count { card ->
            card.getCardDenomination() == Denomination.ACE
        }
    }
}
