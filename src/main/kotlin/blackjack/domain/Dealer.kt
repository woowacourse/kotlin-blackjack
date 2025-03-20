package blackjack.domain

class Dealer(name: String = DEALER_NAME) : Participant(name) {
    fun isBust(): Boolean {
        return this.state.hand.isBust()
    }

    fun play(deck: Deck): Int {
        var count = 0
        while (state.hand.sum() < MINIMUM_STAY_CONDITION) {
            count++
            state = state.draw(deck.draw())
        }
        return count
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
        private const val MINIMUM_STAY_CONDITION = 17
    }
}
