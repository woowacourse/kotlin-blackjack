package blackjack.view

class InputView {
    fun inputParticipants(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }
    }

    fun inputDrawMore(name: String): Boolean {
        println("\n${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val command = readln()
        return when (command) {
            in DRAW_COMMANDS -> true
            in END_TURN_COMMANDS -> false
            else -> inputDrawMore(name)
        }
    }

    fun inputBettingMoney(name: String): Int? {
        println("\n${name}의 베팅 금액은?")
        return runCatching {
            readln().toInt()
        }.onFailure { println("베팅금액은 숫자여야 합니다") }
            .getOrNull() ?: inputBettingMoney(name)
    }

    companion object {
        private val DRAW_COMMANDS = listOf("Y", "y")
        private val END_TURN_COMMANDS = listOf("N", "n")
    }
}
