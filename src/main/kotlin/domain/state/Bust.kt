package domain.state

class Bust : Finished() {
    override fun profit(bet: Double) = 0.0
}
