package blackjack.domain

class Dealer : Participant() {
    fun drawCard() {
        while (calculateScore() <= DEALER_HIT_CONDITION) {
            addCard(Deck.pick())
        }
    }

    companion object {
        private const val DEALER_HIT_CONDITION = 16
    }
}
