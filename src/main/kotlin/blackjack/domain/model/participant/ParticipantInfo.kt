package blackjack.domain.model.participant

import blackjack.domain.model.progress.BetAmount

data class ParticipantInfo(
    val name: String,
    val betAmount: BetAmount,
)
