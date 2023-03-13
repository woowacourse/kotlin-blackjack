package domain.state

import domain.card.Card
import domain.card.Hand
import domain.result.Score
import java.lang.IllegalStateException

abstract class Finished(protected val hand: Hand) : State {
    override fun draw(card: Card): State {
        throw IllegalStateException(CANT_DRAW_ERROR)
    }

    override fun stay(): State {
        throw IllegalStateException(CANT_STAY_ERROR)
    }

    override fun getHandCards() = hand.value

    override fun getScore() = Score.of(hand)

    companion object {
        private const val CANT_DRAW_ERROR = "카드를 하나 더 받을 수 없습니다."
        private const val CANT_STAY_ERROR = "이미 종료된 상태입니다."
    }
}
