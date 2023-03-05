package blackjack.domain.gameResult

data class TotalGameResult(
    val playersGameResult: List<PlayerGameResult>,
    val dealerGameResults: List<GameResult>
)
