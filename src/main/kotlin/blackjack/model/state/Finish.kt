package blackjack.model.state

import blackjack.model.Money
import blackjack.model.card.Card
import blackjack.model.card.Hand

abstract class Finish(
    override val hand: Hand,
) : State {
    abstract val profitRate: Float

    override fun expectedProfit(money: Money): Money = money * profitRate

    override fun profit(): Float = profitRate

    override fun draw(card: Card) = throw IllegalStateException("카드를 추가 할 수 없습니다.")

    override fun stop() = throw IllegalStateException("이미 카드를 더 뽑을 수 없는 상태입니다.")
}
