package blackjack.domain.gameResult

data class PlayerGameResults(private val _playerGameResults: List<PlayerGameResult>) {

    val playerGameResults: List<PlayerGameResult>
        get() = _playerGameResults.toList()

    fun getPlayersTotalProfit(): ProfitMoney {
        val totalProfitValue = _playerGameResults.sumOf { playerGameResult ->
            playerGameResult.profitMoney.value
        }

        return ProfitMoney(totalProfitValue)
    }
}
