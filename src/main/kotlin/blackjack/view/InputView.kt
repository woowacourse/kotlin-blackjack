package blackjack.view

object InputView {
    fun getPlayerNames(): List<String> {
        println(REQUEST_PLAYERS_NAME)
        val input = readln()
        return input.split(",").map { it.trim() }
    }

    fun getPlayerBettingAmount(playerName: String): Int? {
        println(askBettingAmount(playerName))
        val input = readln()
        return runCatching {
            input.toInt()
        }.getOrNull()
    }

    private const val REQUEST_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

    private fun askBettingAmount(name: String): String = "${name}의 배팅 금액은?"
}
