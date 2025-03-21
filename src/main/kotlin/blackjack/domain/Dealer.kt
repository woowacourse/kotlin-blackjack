package blackjack.domain

class Dealer(name: String = DEALER_NAME) : Participant(name) {
    var profit: Double = 0.0
        private set

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

    override fun calculateProfit(stateProfit: Double) {}

    fun calculateProfitWith(players: List<Player>) {
        players.forEach {
            profit += it.profit
        }
    }

    companion object {
        private const val DEALER_NAME: String = "딜러"
        private const val MINIMUM_STAY_CONDITION = 17
    }
}
