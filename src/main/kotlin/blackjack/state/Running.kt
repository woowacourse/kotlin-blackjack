package blackjack.state

import blackjack.model.participant.Player

class Running(player: Player) : State {
    override fun drawCard(): State {
        return this
    }
}
