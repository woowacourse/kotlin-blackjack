package blackjack.state

import blackjack.model.participant.Player

class Start(private val player: Player) : State {
    override fun drawCard(): State {
        player.initializeCards()
        return if (player.isBlackjack()) Blackjack(player) else Running(player)
    }
}
