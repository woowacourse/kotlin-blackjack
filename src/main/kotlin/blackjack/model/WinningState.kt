package blackjack.model

data class WinningState(
    val state: Map<CardHolder, GameResult>,
)
