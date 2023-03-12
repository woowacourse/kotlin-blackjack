package blackjack.domain.state

import blackjack.domain.Card

interface Stopped : State {
    override fun draw(card: Card): State = throw IllegalStateException(STOPPED_STATE_ERROR)

    companion object {
        private const val STOPPED_STATE_ERROR = "더이상 카드를 뽑을수 없는 상태입니다."
    }
}
