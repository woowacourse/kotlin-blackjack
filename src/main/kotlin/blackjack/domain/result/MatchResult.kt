package blackjack.domain.result

import blackjack.domain.money.Money
import blackjack.domain.participant.Participant

data class MatchResult(
    val participant: Participant,
    val profit: Money
    // val winCount: Int,
    // val loseCount: Int,
    // val drawCount: Int
)
