package blackjack.state

import blackjack.model.participant.Player

class Blackjack(private val player: Player) : Finished() {
    override val rate: Double = 1.5
}
