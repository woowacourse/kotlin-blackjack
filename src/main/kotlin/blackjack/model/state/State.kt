package blackjack.model.state

import blackjack.model.Money
import blackjack.model.card.Card
import blackjack.model.card.Hand

interface State {
    val hand: Hand

    fun expectedProfit(money: Money): Money

    fun profit(): Float

    fun draw(card: Card): State

    fun stop(): State
}
