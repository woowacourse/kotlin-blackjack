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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PlayerGameResults

        if (_playerGameResults != other._playerGameResults) return false

        return true
    }

    override fun hashCode(): Int {
        return _playerGameResults.hashCode()
    }
}
