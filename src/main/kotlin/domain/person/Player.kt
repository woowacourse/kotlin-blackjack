package domain.person

import domain.card.Hand
import domain.state.State
import domain.state.playerState.PlayerFirstTurn

class Player(name: String) : Person(name) {
    override var state: State = PlayerFirstTurn(Hand())
}
