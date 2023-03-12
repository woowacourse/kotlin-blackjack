package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.money.Money

abstract class RunningState(cards: Cards) : CardState(cards) {
    override val isRunning: Boolean = true

    override fun stay(): CardState = StayState(cards)

    override fun draw(card: Card): CardState {
        cards.add(card)
        if (nextStateCondition()) {
            return nextState()
        }
        return this
    }

    abstract fun nextStateCondition(): Boolean

    abstract fun nextState(): CardState

    override fun getFirstCard(): Card = cards.getFirstCard()

    override fun profit(other: CardState, money: Money): Money {
        throw IllegalStateException("게임이 종료되지 않아 수익을 계산할 수 없습니다.")
    }
}
