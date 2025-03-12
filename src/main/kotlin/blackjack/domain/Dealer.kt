package blackjack.domain

class Dealer : Participant() {
    override fun canDraw(): Boolean {
        return Rule.calculateScore(hand) <= DEALER_HIT_CONDITION
    }

    fun drawCard(deck: Deck) {
        while (canDraw()) {
            addCard(deck.pick())
        }
    }

    fun getHitCount(): Int {
        return hand.cards.size - Rule.INITIAL_CARD_COUNT
    }

    companion object {
        private const val DEALER_HIT_CONDITION = 16
    }
}
