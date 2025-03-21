package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.Result
import blackjack.domain.card.Card

abstract class Finished(override val hand: Hand) : PlayingState {
    final override fun draw(card: Card): PlayingState {
        throw IllegalStateException(ERROR_CANT_DRAW_CARD)
    }

    abstract fun decideResult(state: PlayingState): Result

   abstract override fun profit(state: PlayingState): Double

    companion object {
        private const val ERROR_CANT_DRAW_CARD = "더 이상 카드를 뽑을 수 없습니다."
    }
}
