package blackjack.domain.result

import blackjack.domain.participant.Participant

data class MatchResult(
    val participant: Participant,
    val winCount: Int,
    val loseCount: Int,
    val drawCount: Int
)
