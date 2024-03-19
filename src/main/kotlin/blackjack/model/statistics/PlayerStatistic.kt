package blackjack.model.statistics

import blackjack.model.GameResult
import blackjack.model.Player

data class PlayerStatistic(
    val player: Player,
    val gameResult: GameResult,
)
