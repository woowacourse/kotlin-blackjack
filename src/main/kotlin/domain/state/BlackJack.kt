package domain.state

class BlackJack : Finished() {
    override fun profit(bet: Double) = bet * BLACK_JACK_RATE

    companion object {
        private const val BLACK_JACK_RATE = 1.5
    }
}
