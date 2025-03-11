package blackjack.domain.model.card

import blackjack.domain.model.participant.CardStatus

class HandCards {
    private val cards: MutableList<Card> = MutableList(2) { Deck.giveCard() }

    fun currentCards(): List<Card> = cards.toList()

    fun addCard(card: Card) {
        cards += card
    }

    fun getStatus(): CardStatus = CardStatus.calculateCardsStatus(cards)
}
