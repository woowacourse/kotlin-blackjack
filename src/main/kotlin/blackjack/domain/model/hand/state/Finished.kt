package blackjack.domain.model.hand.state

import blackjack.domain.model.Card
import blackjack.domain.model.hand.Hands

sealed class Finished(hands: Hands) : BaseState(hands) {
    override fun nextState(card: Card): State {
        throw IllegalStateException("종료된 상태에서 카드를 더 추가 할 수 없습니다.")
    }

    override fun isStarted(): Boolean = false

    override fun isFinished(): Boolean = true

    override fun stay(): Finished = this
}
