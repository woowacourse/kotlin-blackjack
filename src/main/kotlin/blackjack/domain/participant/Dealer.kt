package blackjack.domain.participant

import blackjack.domain.deck.Deck

class Dealer : Participant() {
    override val hitThreshold: Int
        get() = DEALER_HIT_THRESHOLD

    fun setAllCard(deck: Deck) {
        while (canHit()) {
            innerCards.add(deck.draw())
        }
    }

    fun hasAdditionalCard(): Boolean {
        return innerCards.size() > INITIAL_CARD_COUNT
    }

    companion object {
        const val DEALER_HIT_THRESHOLD = 17
        const val INITIAL_CARD_COUNT = 2
    }
}
