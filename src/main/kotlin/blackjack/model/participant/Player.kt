package blackjack.model.participant

import blackjack.model.state.StartGame
import blackjack.model.state.State

class Player(
    name: String,
    state: State = StartGame(),
) : Participant(name, state) {
    init {
        require(name != DEALER) { ERROR_MESSAGE_NAME }
        require(name.length in NAME_LENGTH_MIN..NAME_LENGTH_MAX) { ERROR_MESSAGE_NAME_LENGTH }
    }

    companion object {
        const val DEALER = "딜러"
        const val ERROR_MESSAGE_NAME_LENGTH = "플레이어는 1에서 5사이 길이의 이름만 가질 수 있습니다."
        const val ERROR_MESSAGE_NAME = "플레이어는 딜러라는 이름을 가질 수 없습니다."

        const val NAME_LENGTH_MIN = 1
        const val NAME_LENGTH_MAX = 5
    }
}
