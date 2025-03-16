package blackjack.domain.model.participant

import blackjack.domain.model.participant.bet.BetAmount

data class ParticipantInfo(
    val name: String,
    val betAmount: BetAmount,
)
