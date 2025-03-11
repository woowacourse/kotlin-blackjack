package blackjack.domain

import blackjack.domain.participant.Player

data class PlayerResult(val player: Player, val status: GameResultStatus)
