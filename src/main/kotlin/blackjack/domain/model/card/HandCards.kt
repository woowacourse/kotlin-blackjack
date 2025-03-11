package blackjack.domain.model.card

import blackjack.domain.model.card.Number.ACE
import blackjack.domain.model.participant.CardStatus

class HandCards {
    private val cards: MutableList<Card> = MutableList(2) { Deck.giveCard() }

    fun currentCards(): List<Card> = cards.toList()

    fun getCardByIndex(index: Int): Card = cards[index]

    fun addCard(card: Card) {
        cards += card
    }

    fun getStatus(): CardStatus = CardStatus.calculateCardsStatus(cards)

    fun calculateBestCardValue(): Int {
        val cardNumbers = cards.map { it.number }
        val minimumSum = calculateCardValueMinimumSum(cards)

        if (ACE in cardNumbers && (minimumSum + ACE_VALUE_GAP) <= CardStatus.BLACKJACK_NUMBER) {
            return minimumSum + ACE_VALUE_GAP
        }
        return minimumSum
    }

    private fun calculateCardValueMinimumSum(cards: Collection<Card>): Int {
        val cardValues = cards.map { it.getMinimumValue() }
        return cardValues.sum()
    }

    companion object {
        private const val ACE_VALUE_GAP = 10
    }
}
