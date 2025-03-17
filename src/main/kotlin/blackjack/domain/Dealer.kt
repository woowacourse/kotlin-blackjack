package blackjack.domain

class Dealer(name: String = DEALER_NAME) : Participant(name) {
    fun isBust(): Boolean {
        return this.state.hand.sum() > 21
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
    }
}
