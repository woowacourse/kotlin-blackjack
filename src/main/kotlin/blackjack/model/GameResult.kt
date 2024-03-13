package blackjack.model

data class GameResult(
    val dealerResult: Map<Result, Int>,
    val playerResults: Map<Player, Result>,
)
