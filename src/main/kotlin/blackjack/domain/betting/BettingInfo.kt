package blackjack.domain.betting

import blackjack.domain.participants.Player

data class BettingInfo(
    val player: Player,
    val bettingAmount: BettingAmount,
)
