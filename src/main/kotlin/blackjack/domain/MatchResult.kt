package blackjack.domain

data class MatchResult(
    val participant: Participant,
    val winCount: Int,
    val loseCount: Int,
    val drawCount: Int
)
