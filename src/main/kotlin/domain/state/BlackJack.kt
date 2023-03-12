package domain.state

import domain.card.HandOfCards

class BlackJack(override val handOfCards: HandOfCards) : Finished() {
    override fun playerProfit(other: State, bet: Double): Double {
        if (other is BlackJack) return 0.0
        return bet * BLACK_JACK_RATE
    }

    companion object {
        private const val BLACK_JACK_RATE = 1.5
    }
}
