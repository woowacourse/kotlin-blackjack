package blackjack.domain.model.participant

import blackjack.domain.model.participant.bet.BetAmount

data class ParticipantInfo(
    val name: String = "이름 없음",
    val betAmount: BetAmount,
)
