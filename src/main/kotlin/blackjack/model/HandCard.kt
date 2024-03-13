package blackjack.model

class HandCard {
    private var _cards: List<Card> = listOf()
    val cards: List<Card> get() = _cards

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

    fun checkBlackJackStateWithCardCount(): Boolean {
        return cards.size == GameManager.INIT_HAND_CARD_COUNT
    }

    fun updateGameStateWithAceCount(totalScore: Int): Boolean {
        if (getAceCount() > MIN_ACE_COUNT) {
            return checkCurrentScore(totalScore + Denomination.TRANSFER_ACE_SCORE)
        }
        return false
    }

    private fun checkCurrentScore(currentScore: Int): Boolean {
        return currentScore == BlackJack.BLACK_JACK_SCORE
    }

    companion object {
        private const val MIN_ACE_COUNT: Int = 0
    }
}
