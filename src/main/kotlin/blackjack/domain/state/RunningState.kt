package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.money.Money

abstract class RunningState(private val cards: Cards) : CardState {
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

    override fun profit(money: Money): Money {
        throw IllegalStateException("게임이 종료되지 않아 수익을 계산할 수 없습니다.")
    }
}
