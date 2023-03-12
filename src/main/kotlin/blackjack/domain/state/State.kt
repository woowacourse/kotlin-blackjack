package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.CardBunchForState

interface State {
    val hand: CardBunchForState

    fun draw(card: Card): State = throw IllegalStateException(STOPPED_STATE_ERROR)

    fun stay(): State = throw IllegalStateException(UNAUTHORIZED_FUNCTION_ERROR)

    companion object {
        private const val STOPPED_STATE_ERROR = "더이상 카드를 뽑을수 없는 상태입니다."
        private const val UNAUTHORIZED_FUNCTION_ERROR = "해당 기능은 현재 상태에서 사용할수 없습니다."
    }
}
