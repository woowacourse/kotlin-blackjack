package blackjack.view

object InputView {
    private const val REQUEST_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val REQUEST_DRAW_COMMAND = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun inputNames(): List<String> {
        println(REQUEST_PLAYER_NAMES)
        printInterval()
        return readln().split(",")
    }

    fun inputDrawCommand(name: String): String {
        println(REQUEST_DRAW_COMMAND.format(name))
        return readln()
    }

    private fun printInterval() = println()
}
