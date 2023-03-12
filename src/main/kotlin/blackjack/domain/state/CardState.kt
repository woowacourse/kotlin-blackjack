package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money

abstract class CardState(protected val cards: Cards) {
    abstract val isRunning: Boolean

    abstract fun draw(card: Card): CardState

    abstract fun stay(): CardState

    abstract fun profit(other: CardState, betMoney: BetMoney): Money

    abstract fun getFirstCard(): Card

    fun getAllCards(): List<Card> = cards.items

    fun getTotalScore(): Int = cards.calculateTotalScore()
}
