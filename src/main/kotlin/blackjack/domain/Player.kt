package blackjack.domain

class Player(name: String, val bettingAmount: Money) : Participant(name) {
    var profit: Double = 0.0
        private set
    override fun calculateProfit(stateProfit: Double) {
        profit = stateProfit * bettingAmount.amount
    }
}
