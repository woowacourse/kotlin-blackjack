package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money

abstract class RunningState(cards: Cards) : CardState(cards) {
    override val isRunning: Boolean = true

    override fun stay(): CardState = StayState(cards)

    override fun getFirstCard(): Card = cards.getFirstCard()

    override fun profit(other: CardState, betMoney: BetMoney): Money {
        throw IllegalStateException("게임이 종료되지 않아 수익을 계산할 수 없습니다.")
    }
}
