package blackjack.domain

data class BlackjackResult(
    val cardResults: List<CardResult>,
    val matchResults: List<MatchResult>,
)
