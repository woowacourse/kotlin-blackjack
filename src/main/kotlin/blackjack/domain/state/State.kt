package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.CardBunch

interface State : Comparable<State> {
    val hand: CardBunch
    fun draw(card: Card): State

    fun stay(): State = throw IllegalStateException(UNAUTHORIZED_FUNCTION_ERROR)

    override fun compareTo(other: State): Int {
        return when {
            this is BlackJack && other !is BlackJack -> 1
            this is BlackJack && other is BlackJack -> 0
            this !is BlackJack && other is BlackJack -> -1
            this is Burst && other !is Burst -> -1
            this is Burst && other is Burst -> 0
            this !is Burst && other is Burst -> 1
            else -> 0
        }
    }

    companion object {
        private const val UNAUTHORIZED_FUNCTION_ERROR = "해당 기능은 현재 상태에서 사용할수 없습니다."
    }
}
