package domain.state

import domain.card.Card
import domain.card.HandOfCards

interface State {
    val handOfCards: HandOfCards
    fun nextState(card: Card): State
    fun toStay(): State
    fun playerProfit(other: State, bet: Double): Double
}
