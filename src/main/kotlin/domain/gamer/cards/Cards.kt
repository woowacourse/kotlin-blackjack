package domain.gamer.cards

import domain.card.Card
import domain.card.CardValue
import domain.gamer.Participant.Companion.START_DECK_CARD_COUNT

class Cards(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getCards(): List<Card> {
        return _cards.toList()
    }

    fun calculateCardSum(): Int {
        val cardSum = _cards
            .sumOf { it.cardValue.value }
        return cardSum +
            if (isAceValueToEleven(cardSum)) CardValue.ACE_VALUE_GAP else 0
    }

    private fun isAceValueToEleven(cardSum: Int): Boolean {
        return isAceContained() && cardSum <= CardValue.ACE_ELEVEN_VALUE
    }

    private fun isAceContained() = _cards.any { it.cardValue.title == CardValue.ACE.title }

    fun isBurst(): Boolean = calculateCardSum() > CARD_SUM_MAX_VALUE

    fun checkBlackjack(): Boolean =
        calculateCardSum() == CARD_SUM_MAX_VALUE && _cards.size == START_DECK_CARD_COUNT

    companion object {
        const val CARD_SUM_MAX_VALUE = 21
    }
}
