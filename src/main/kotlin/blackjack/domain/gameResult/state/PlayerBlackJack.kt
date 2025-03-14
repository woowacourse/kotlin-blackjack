package blackjack.domain.gameResult.state

import blackjack.domain.gameResult.GameResult
import blackjack.domain.participant.Player

data class PlayerBlackJack(override val player: Player) : PlayerState {
    override val totalSum: Int
        get() = player.getTotalSum()
    override val earnRate: Double
        get() = 1.5

    override fun compare(state: State): GameResult {
        if (state is BlackJack) return GameResult.DRAW
        return GameResult.WIN
    }
}
