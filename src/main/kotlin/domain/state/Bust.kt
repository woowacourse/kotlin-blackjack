package domain.state

import domain.card.HandOfCards

class Bust(override val handOfCards: HandOfCards) : Finished() {
    override fun playerProfit(other: State, bet: Double): Double = bet * -1
}
