package domain.state

class Stay : Finished() {
    override fun profit(bet: Double) = bet
}
