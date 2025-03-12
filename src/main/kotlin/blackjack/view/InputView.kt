package blackjack.view

import blackjack.GameController.Companion.RETRY_COUNT
import blackjack.global.NullableRetry

object InputView : NullableRetry {
    fun getPlayerNames(): List<String> {
        println(REQUEST_PLAYERS_NAME)
        val input = readln()
        return input.split(",").map { it.trim() }
    }

    fun getPlayerBettingAmount(playerName: String): Int {
        println(askBettingAmount(playerName))
        return retryUntilValidInput(RETRY_COUNT) {
            runCatching {
                val input = readln()
                input.toInt()
            }.getOrNull()
        }
    }

    override fun onOnceFailure() {
        println("올바르지 않은 값입니다 다시 시도해주세요")
    }

    private const val RETRY_COUNT = 3
    private const val REQUEST_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

    private fun askBettingAmount(name: String): String = "${name}의 배팅 금액은?"
}
