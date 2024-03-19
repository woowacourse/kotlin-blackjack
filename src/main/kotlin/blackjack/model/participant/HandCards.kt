package blackjack.model.participant

import blackjack.model.card.Card

class HandCards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getCardSum(): Int {
        var cardSum = 0
        _cards.forEach {
            cardSum += it.getScore()
        }
        repeat(getAceCount()) {
            if (cardSum + ADDITIONAL_ACE_SCORE > BURST_CONDITION) return cardSum
            cardSum += ADDITIONAL_ACE_SCORE
        }
        return cardSum
    }

    private fun getAceCount(): Int {
        return _cards.count { it.denomination.isAce() }
    }

    companion object {
        private const val BURST_CONDITION = 21
        private const val ADDITIONAL_ACE_SCORE = 10
    }
}
