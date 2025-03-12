package blackjack.domain

class Dealer : Participant() {
    override fun canDraw(): Boolean {
        return Rule.calculateScore(hand) <= DEALER_HIT_CONDITION
    }

    fun drawCard() {
        while (canDraw()) {
            addCard(Deck.pick())
        }
    }

    companion object {
        private const val DEALER_HIT_CONDITION = 16
    }
}
