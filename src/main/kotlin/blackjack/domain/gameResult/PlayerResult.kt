package blackjack.domain.gameResult

import blackjack.domain.Player

data class PlayerResult(val player: Player, val status: GameResultStatus)
