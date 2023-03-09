package view

class ResultView(private val output: Output) {
    fun printDealerStatus(cards: String, result: Int) {
        output.println(MESSAGE_DEALER_GAME_STATUS.format(cards, result))
    }
    fun printPlayerStatus(playersName: List<String>, playersCards: List<String>, playersResult: List<Int>) {
        playersName.forEachIndexed { index, _ ->
            output.println(MESSAGE_PLAYERS_GAME_STATUS.format(playersName[index], playersCards[index], playersResult[index]))
        }
    }

    fun printGameResult(dealerGameResult: Map<String, Int>, playersGameResult: Map<String, String>) {
        output.println(MESSAGE_GAME_RESULT)
        output.println(MESSAGE_DEALER_GAME_RESULT.format(formatDealerGameResult(dealerGameResult)))
        playersGameResult.forEach {
            output.println(MESSAGE_PLAYERS_GAME_RESULT.format(it.key, it.value))
        }
    }

    fun printProfitResult(dealerProfit: Int, playersName: List<String>, playersProfit: List<Int>) {
        output.println(MESSAGE_PROFIT_RESULT)
        output.println(MESSAGE_DEALER_PROFIT_RESULT.format(dealerProfit))
        playersName.zip(playersProfit).forEach {
            output.println(MESSAGE_PLAYERS_PROFIT_RESULT.format(it.first, it.second))
        }
    }

    private fun formatDealerGameResult(dealerGameResult: Map<String, Int>): String {
        return dealerGameResult.asSequence().joinToString(" ") {
            MESSAGE_GAME_RESULT_TYPE.format(it.value, it.key)
        }
    }

    companion object {
        private const val MESSAGE_DEALER_GAME_STATUS = "딜러 카드: %s - 결과: %d"
        private const val MESSAGE_PLAYERS_GAME_STATUS = "%s카드: %s - 결과: %d"
        private const val MESSAGE_GAME_RESULT = "## 최종 승패"
        private const val MESSAGE_PROFIT_RESULT = "## 최종 수익"
        private const val MESSAGE_DEALER_GAME_RESULT = "딜러: %s"
        private const val MESSAGE_PLAYERS_GAME_RESULT = "%s: %s"
        private const val MESSAGE_DEALER_PROFIT_RESULT = "딜러: %d"
        private const val MESSAGE_PLAYERS_PROFIT_RESULT = "%s: %d"
        private const val MESSAGE_GAME_RESULT_TYPE = "%d%s"
    }
}
