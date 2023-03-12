package blackjack.domain

enum class EarningRate(private val rate: Double) {
    DRAW(0.0),
    LOSE(-1.0),
    WIN(1.0);

    fun of(isBlackjack: Boolean): Double {
        if ((this == WIN) and isBlackjack) return BLACKJACK_EARNING_RATE
        return this.rate
    }

    companion object {
        private const val BLACKJACK_EARNING_RATE = 1.5
    }
}
