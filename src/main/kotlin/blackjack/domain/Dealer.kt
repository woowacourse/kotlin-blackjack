package blackjack.domain

import blackjack.domain.state.Hit

class Dealer : Participant() {
    var profit: Double = 0.0
        private set

    fun drawInitialCards(deck: Deck) {
        repeat(2) { drawCard(deck.draw()) }
    }

    fun getVisibleCard(): Card = state.hand.cards.first()

    override fun drawMoreCard(): Boolean = state.hand.getTotalScore() < 17

    override fun stay() {
        if (state is Hit) {
            state = (state as Hit).changeStay()
        }
    }

    fun playTurn(deck: Deck) {
        while (drawMoreCard()) {
            drawCard(deck.draw())
        }
        stay()
    }
}
