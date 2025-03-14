package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Player

data class PlayerBust(override val player: Player) : PlayerState {
    override val totalSum: Int
        get() = player.getTotalSum()
    override val earnRate: Double
        get() = 1.0

    override fun compare(state: State): GameResult {
        return GameResult.LOSE
    }
}
