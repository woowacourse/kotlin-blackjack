package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

abstract class Finished(open val hand: Hand, val profit: Double) : PlayingState {
    override fun draw(card: Card): PlayingState {
        throw IllegalStateException(ERROR_CANT_DRAW_CARD)
    }

    companion object {
        private const val ERROR_CANT_DRAW_CARD = "더 이상 카드를 뽑을 수 없습니다."
    }
}
