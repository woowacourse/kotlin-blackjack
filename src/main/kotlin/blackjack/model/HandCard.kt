package blackjack.model

class HandCard {
    private var _cards: List<Card> = listOf()
    var cards: List<Card>
        get() = _cards
        private set(value) {
            _cards = value
        }

    fun addCard(card: Card) {
        _cards += card
    }

    fun getTotalCardsSum(): Int {
        return cards.sumOf { card ->
            card.denomination.score
        }
    }

    fun getAceCount(): Int {
        return cards.count { card ->
            card.denomination == Denomination.ACE
        }
    }

    fun checkStateWithCardCount(): Boolean {
        return cards.size == GameManager.INIT_HAND_CARD_COUNT
    }

    fun checkStateWithAceCount(): Boolean {
        return cards.count { it.denomination == Denomination.ACE } >= MIN_ACE_COUNT
    }

    companion object {
        private const val MIN_ACE_COUNT = 1
    }
}
