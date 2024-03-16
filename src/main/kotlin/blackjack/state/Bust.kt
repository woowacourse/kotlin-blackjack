package blackjack.state

import blackjack.model.participant.Player

class Bust(private val player: Player) : Finished()
