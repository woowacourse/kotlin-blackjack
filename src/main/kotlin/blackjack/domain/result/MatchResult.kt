package blackjack.domain.result

import blackjack.domain.participant.Participant

data class MatchResult(
    val participant: Participant,
    val total: Int,
)
