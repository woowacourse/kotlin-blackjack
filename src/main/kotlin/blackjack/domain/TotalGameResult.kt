package blackjack.domain

data class TotalGameResult(

    val playersGameResult: List<PlayerGameResult>,
    val dealerGameResults: List<GameResult>
)
