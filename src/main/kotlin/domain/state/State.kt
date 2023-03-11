package domain.state

import domain.card.Card
import domain.money.Money

interface State {
    fun draw(card: Card): State
    fun stay(): State
    fun getHandCards(): List<Card>
    fun profit(money: Money): Double
}
