package blackjack.domain.gameResult

data class TotalGameResult(
    val playerGameResults: List<PlayerGameResult>,
    val dealerGameResults: ProfitMoney
)
