package domain.state

import domain.card.HandOfCards

class Stay(override val handOfCards: HandOfCards) : Finished() {
    override fun profit(bet: Double) = bet
}
