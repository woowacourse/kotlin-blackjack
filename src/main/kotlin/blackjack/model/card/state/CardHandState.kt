package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.result.Money
import blackjack.model.result.Score

interface CardHandState {
    fun draw(card: Card): CardHandState

    fun stay(): CardHandState

    fun getCardHands(): CardHand

    fun countCards(): Int

    fun getCardHandScore(): Score

    fun calculateProfit(
        money: Money,
        other: CardHandState,
    ): Money
}
