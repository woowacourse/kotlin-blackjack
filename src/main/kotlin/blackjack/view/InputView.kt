package blackjack.view

object InputView {
    private const val PLAYER_NAME_DELIMITER = ","

    fun inputNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(PLAYER_NAME_DELIMITER).map { it.trim() }
        printInterval()
        return names
    }

    fun inputBettingMoney(name: String): Int {
        println("${name}의 베팅 금액은?")
        return checkBettingMoney(readln())
            .onSuccess { printInterval() }
            .onFailure { println(it.message) }
            .getOrElse { inputBettingMoney(name) }
    }

    private fun checkBettingMoney(input: String): Result<Int> {
        return runCatching {
            val money = input.toIntOrNull()
            requireNotNull(money) { "베팅 금액은 숫자여야 합니다. 베팅 금액은 '$input' 일 수 없습니다." }
            require(money >= 500) { "베팅 금액은 500원 이상이어야 합니다. 베팅 금액은 '$input' 일 수 없습니다." }
            money
        }
    }

    fun inputDrawCommand(name: String): Boolean = runCatching {
        println("${name}은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        val command = readln().trim().lowercase()
        require(command in listOf("y", "n")) { "입력값은 y 또는 n 이어야 합니다. (현재 입력값 : $command)" }
        return@runCatching command == "y"
    }.getOrElse {
        println(it.message)
        inputDrawCommand(name)
    }

    private fun printInterval() = println()
}
