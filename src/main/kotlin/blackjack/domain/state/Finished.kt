package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.Result

abstract class Finished(
    override val hand: Hand,
) : State {
    final override fun draw(card: Card): State = throw IllegalArgumentException("더 이상 카드를 뽑을 수 없습니다.")

    abstract fun checkResult(state: State): Result

    abstract override fun profit(state: State): Double
}
