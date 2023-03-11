package blackjack.view

import blackjack.domain.money.BetMoney
import blackjack.domain.money.Money
import blackjack.domain.participant.Player

object InputView {
    private const val PLAYER_NAME_DELIMITER = ","

    fun inputPlayers(): List<Player> = inputNames().map { name ->
        val betAmount = inputBetAmount(name)
        Player(name = name, money = betAmount) { inputDrawCommand(name) }
    }

    private fun inputNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val names = readln().split(PLAYER_NAME_DELIMITER).map { it.trim() }
        printInterval()
        return names
    }

    private fun inputBetAmount(name: String): Money = runCatching {
        println("${name}의 배팅 금액은?")
        val amount = readln().trim().toIntOrNull()
        requireNotNull(amount) { "배팅 금액은 숫자를 입력해주셔야 합니다." }
        printInterval()
        return@runCatching BetMoney(amount)
    }.getOrElse {
        println(it.message)
        inputBetAmount(name)
    }

    private fun inputDrawCommand(name: String): Boolean = runCatching {
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
