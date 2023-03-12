package state

import domain.card.Card
import domain.card.Cards

interface State {
    val isFinished: Boolean
    val rateOfProfit: Double
    val resultScore: Int
    fun draw(card: Card): State
    fun next(nextCards: Cards): State
    fun stay(): State
    fun getCards(): List<Card>
    fun resultProfit(other: State): Double
}
