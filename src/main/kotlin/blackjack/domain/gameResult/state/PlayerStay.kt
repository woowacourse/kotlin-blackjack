package blackjack.domain.gameResult.state

import blackjack.domain.participant.Player

data class PlayerStay(override val player: Player) : PlayerState {
    override val totalSum: Int
        get() = player.getTotalSum()
    override val earnRate: Double
        get() = 1.0
}
