package domain.state

import domain.card.HandOfCards

class Stay(override val handOfCards: HandOfCards) : Finished() {
    override fun playerProfit(other: State, bet: Double): Double {
        val otherScore = other.handOfCards.getTotalCardSum()
        val myScore = handOfCards.getTotalCardSum()
        return when {
            other is Bust -> bet
            other is BlackJack -> bet.convertPositiveNegative()
            otherScore > myScore -> bet.convertPositiveNegative()
            otherScore < myScore -> bet
            else -> 0.0
        }
    }
}
