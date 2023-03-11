package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.money.Money

interface CardState {
    val isFinished: Boolean

    fun draw(card: Card): CardState

    fun stay(): CardState

    fun profit(money: Money): Money

    fun getAllCards(): List<Card>

    fun getFirstCard(): Card

    fun getTotalScore(): Int
}
