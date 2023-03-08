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

    companion object {
        private val DRAW_COMMANDS = listOf("Y", "y")
        private val END_TURN_COMMANDS = listOf("N", "n")
    }
}
