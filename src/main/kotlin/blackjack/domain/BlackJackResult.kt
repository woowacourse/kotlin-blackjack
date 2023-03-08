package blackjack.domain

data class BlackJackResult(
    val cardResults: List<CardResult>,
    val matchResults: List<MatchResult>,
)
