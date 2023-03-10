package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.money.Money

interface CardState {
    fun draw(card: Card): CardState

    fun stay(): CardState

    fun profit(money: Money): Money
}
