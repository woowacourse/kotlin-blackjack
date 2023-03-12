package domain.state

import domain.card.Card
import domain.money.Money
import domain.money.Profit
import domain.result.Score

interface State {
    fun draw(card: Card): State
    fun stay(): State
    fun getHandCards(): List<Card>
    fun getScore(): Score
    fun profit(other: State, money: Money): Profit
}
