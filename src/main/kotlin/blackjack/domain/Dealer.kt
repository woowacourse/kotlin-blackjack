package blackjack.domain

class Dealer : Participant() {
    override val hitThreshold: Int
        get() = DEALER_HIT_THRESHOLD

    fun getCard(deck: Deck) {
        while (canHit()) {
            addCard(deck.draw())
        }
    }

    fun haveAdditionalCard(): Boolean {
        return cards.size > 2
    }

    companion object {
        const val DEALER_HIT_THRESHOLD = 17
    }
}
