package blackjack.model

class HandCard {
    private var cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> {
        return cards
    }

    fun getGameScore(): Int {
        val score = getTotalCardsSum()
        val transferScore = score + Denomination.TRANSFER_ACE_SCORE
        return if (hasMinimumAces() && transferScore <= BlackJack.BLACK_JACK_SCORE) {
            transferScore
        } else {
            score
        }
    }

    private fun getTotalCardsSum(): Int {
        return cards.sumOf { card ->
            card.denomination.score
        }
    }

    fun getAceCount(): Int {
        return cards.count { card ->
            card.denomination == Denomination.ACE
        }
    }

    fun isInitialHandSize(): Boolean {
        return cards.size == GameManager.INIT_HAND_CARD_COUNT
    }

    private fun hasMinimumAces(): Boolean {
        return cards.count { it.denomination == Denomination.ACE } >= MIN_ACE_COUNT
    }

    companion object {
        private const val MIN_ACE_COUNT = 1
    }
}
