package domain.state

import domain.card.Card

abstract class Finished : State {
    override fun nextState(card: Card) = throw IllegalStateException("이미 끝났습니다. 다음 State가 없습니다.")
    fun Double.convertPositiveNegative() = this * -1
}
