package blackjack.domain.model.card

import blackjack.domain.model.card.Number.ACE
import blackjack.domain.model.participant.CardStatus

class HandCards(
    initCards: List<Card> = emptyList(),
) {
    private val _cards: MutableList<Card> = initCards.toMutableList()

    val cards: List<Card>
        get() = this._cards.toList()

    fun getCardByIndex(index: Int): Card = this._cards[index]

    fun addCard(card: Card) {
        this._cards += card
    }

    fun getStatus(): CardStatus = CardStatus.calculateCardsStatus(this._cards)

    fun calculateBestCardValue(): Int {
        val cardNumbers = this._cards.map { it.number }
        val minimumSum = calculateCardValueMinimumSum(this._cards)

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
        const val INIT_CARD_SIZE = 2
    }
}
