package blackjack.model

class GameResult(
    val dealerResult: Map<WinningResult, Int>,
    val playerResults: Map<String, WinningResult>,
)
