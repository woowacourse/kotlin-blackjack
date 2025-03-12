package blackjack.view

import blackjack.GameController.Companion.RETRY_COUNT

object InputView {
    fun getPlayerNames(): List<String> {
        println(REQUEST_PLAYERS_NAME)
        val input = readln()
        return input.split(",").map { it.trim() }
    }

    fun getPlayerBettingAmount(playerName: String): Int {
        println(askBettingAmount(playerName))
        return retryUntilValidInput {
            runCatching {
                val input = readln()
                input.toInt()
            }.getOrNull()
        }
    }

    private fun <T> retryUntilValidInput(action: () -> T?): T {
        var tried = 0
        var result: T? = null
        while (result == null && tried < RETRY_COUNT) {
            result = action()
            tried++
        }
        requireNotNull(result) { ERR_INVALID_FORMAT }
        return result
    }

    private const val REQUEST_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val RETRY_COUNT = 3
    private const val ERR_INVALID_FORMAT = "올바르지 않은 형식"

    private fun askBettingAmount(name: String): String = "${name}의 배팅 금액은?"
}
