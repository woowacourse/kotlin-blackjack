package blackjack.domain.result

data class BlackjackResult(
    val cardResults: List<CardResult>,
    val matchResults: List<MatchResult>,
)
