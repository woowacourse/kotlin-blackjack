package domain.person

import domain.card.Hand
import domain.state.State
import domain.state.playerState.PlayerFirstTurn

class Player2(name: String) : Person2(name) {
    override var state: State = PlayerFirstTurn(Hand())
}
