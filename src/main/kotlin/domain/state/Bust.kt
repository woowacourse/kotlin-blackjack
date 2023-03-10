package domain.state

import domain.card.HandOfCards

class Bust(override val handOfCards: HandOfCards) : Finished() {
    override fun profit(bet: Double) = 0.0
}
