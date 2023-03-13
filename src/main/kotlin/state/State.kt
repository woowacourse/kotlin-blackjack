package state

import domain.Score
import domain.card.Card
import domain.card.Cards

interface State {
    val isFinished: Boolean
    val rateOfProfit: RateOfProfit
    val score: Score
    fun draw(card: Card): State
    fun next(nextCards: Cards): State
    fun stay(): State
    fun getCards(): List<Card>
    fun resultProfit(other: State): RateOfProfit
}
