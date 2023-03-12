package blackjack.domain.gameResult

data class TotalGameResult(
    val playerGameResults: PlayerGameResults,
    val dealerGameResults: ProfitMoney
)
