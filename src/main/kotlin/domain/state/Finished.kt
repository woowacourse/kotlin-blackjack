package domain.state

import domain.card.Card

abstract class Finished : State {
    override fun nextState(card: Card) = throw IllegalStateException("이미 끝났습니다. 다음 State가 없습니다.")
    override fun toStay() = throw IllegalStateException("이미 끝났습니다. stay를 호출할 수 없습니다.")
}
