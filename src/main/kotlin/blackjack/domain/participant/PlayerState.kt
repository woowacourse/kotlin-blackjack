package blackjack.domain.participant

import blackjack.domain.BettingAmount

data class PlayerState(
    val name: String,
    val money: BettingAmount,
)
