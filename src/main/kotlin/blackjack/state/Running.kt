package blackjack.state

import blackjack.model.participant.Player

class Running(private val player: Player) : State {
    override fun decisionState(): State {
        return this
    }
}
