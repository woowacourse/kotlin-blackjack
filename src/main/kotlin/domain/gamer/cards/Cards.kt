package domain.gamer.cards

import domain.card.Card
import domain.card.CardValue
import domain.gamer.Participant.Companion.START_DECK_CARD_COUNT

class Cards(private var cards: List<Card>) {

    fun addCard(card: Card) {
        cards = cards.plus(card)
    }

    fun getCards(): List<Card> {
        return cards.toList()
    }

    fun calculateCardSum(): Int {
        val cardSum = cards
            .sumOf { it.cardValue.value }
        return cardSum +
            if (isAceValueToEleven(cardSum)) CardValue.ACE_VALUE_GAP else 0
    }

    private fun isAceValueToEleven(cardSum: Int): Boolean {
        return isAceContained() && cardSum <= CardValue.ACE_ELEVEN_VALUE
    }

    private fun isAceContained() = cards.any { it.cardValue.title == CardValue.ACE.title }

    fun isBurst(): Boolean = calculateCardSum() > CARD_SUM_MAX_VALUE

    fun checkBlackjack(): Boolean =
        calculateCardSum() == CARD_SUM_MAX_VALUE && cards.size == START_DECK_CARD_COUNT

    companion object {
        const val CARD_SUM_MAX_VALUE = 21
    }
}
