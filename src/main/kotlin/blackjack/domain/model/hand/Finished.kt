package blackjack.domain.model.hand

import blackjack.domain.model.Card

sealed class Finished(hands: Hands) : Initial(hands) {
    override fun nextState(card: Card): State {
        throw IllegalStateException("종료된 상태에서 카드를 더 추가 할 수 없습니다.")
    }

    override fun isFinished(): Boolean = true

    override fun stay(): Finished = this
}
