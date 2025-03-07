package blackjack.domain

class Dealer : Participant() {
    override val hitThreshold: Int
        get() = DEALER_HIT_THRESHOLD

    fun getCard(deck: Deck) {
        while (canHit()) {
            addCard(deck.draw())
        }
    }

    fun hasAdditionalCard(): Boolean {
        return cards.size > INITIAL_CARD_COUNT
    }

    companion object {
        const val DEALER_HIT_THRESHOLD = 17
        const val INITIAL_CARD_COUNT = 2
    }
}
