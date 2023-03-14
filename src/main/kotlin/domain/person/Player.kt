package domain.person

import domain.card.HandOfCards
import domain.state.PlayerFirstTurn
import domain.state.State
import domain.state.Stay

class Player(
    override val name: String,
) : Person() {
    override var state: State = PlayerFirstTurn(HandOfCards())

    fun toStay() {
        if (isInProgress()) {
            state = Stay(state.handOfCards)
            return
        }
        throw IllegalStateException("이미 끝났습니다. stay를 호출할 수 없습니다.")
    }
}
