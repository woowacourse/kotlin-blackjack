package blackjack.domain.state

import blackjack.domain.Money
import blackjack.domain.card.Card
import blackjack.domain.card.Hand

interface State {
    val hand: Hand
    val bettingMoney: Money?

    fun betting(money: Money): State
    fun draw(card: Card): State
    fun getScore(): Int = hand.getScore()
    fun stay(): State
    fun getProfit(): Double
}
