package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.Money

interface State {
    val hand: Hand
    val bettingMoney: Money?

    fun betting(money: Money): State
    fun draw(card: Card): State
    fun getScore(): Int = hand.getScore()
    fun stay(): State
    fun getProfit(): Double
}
