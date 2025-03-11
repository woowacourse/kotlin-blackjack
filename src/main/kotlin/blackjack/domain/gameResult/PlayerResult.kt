package blackjack.domain.gameResult

import blackjack.domain.participant.Player

data class PlayerResult(val player: Player, val status: GameResultStatus)
