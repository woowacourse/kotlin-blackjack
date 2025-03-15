package blackjack.domain.model

import blackjack.domain.model.participant.Player

class BetStatus(
    val player: Player,
    val betAmount: BetAmount,
)
