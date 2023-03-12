package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.CardBunch

interface State {
    val hand: CardBunch

    fun draw(card: Card): State

    fun stay(): State = throw IllegalStateException(UNAUTHORIZED_FUNCTION_ERROR)

    companion object {
        private const val UNAUTHORIZED_FUNCTION_ERROR = "해당 기능은 현재 상태에서 사용할수 없습니다."
    }
}
