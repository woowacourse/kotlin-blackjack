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
        return cards
            .sumOf { it.cardValue.value } +
            if (isAceValueToEleven()) CardValue.ACE_VALUE_GAP else 0
    }

    private fun isAceValueToEleven(): Boolean {
        return checkAceContained() && cards
            .sumOf { it.cardValue.value } <= CardValue.ACE_ELEVEN_VALUE
    }

    private fun checkAceContained() = cards.any { it.cardValue.title == CardValue.ACE.title }

    fun checkBurst(): Boolean = calculateCardSum() > CARD_SUM_MAX_VALUE

    fun checkBlackjack(): Boolean =
        calculateCardSum() == CARD_SUM_MAX_VALUE && cards.size == START_DECK_CARD_COUNT

    companion object {
        private const val ACE_COUNT_VALUE_CHANGE_CONDITION = 2
        const val CARD_SUM_MAX_VALUE = 21
    }
}
