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

    companion object {
        private const val DEALER_HIT_CONDITION = 16
    }
}
