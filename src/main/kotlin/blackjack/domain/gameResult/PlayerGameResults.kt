package blackjack.domain.gameResult

class PlayerGameResults(playerGameResults: List<PlayerGameResult>) {

    private val _playerGameResults: List<PlayerGameResult> = playerGameResults.toList()
    val playerGameResults: List<PlayerGameResult>
        get() = _playerGameResults.toList()

    fun getPlayersTotalProfit(): ProfitMoney {
        val totalProfitValue = _playerGameResults.sumOf { playerGameResult ->
            playerGameResult.profitMoney.value
        }

        return ProfitMoney(totalProfitValue)
    }
}
