package blackjack.view

import blackjack.domain.participants.Money
import blackjack.domain.participants.Name

class InputView {
    fun inputParticipants(): List<Name> {
        return runCatching { getParticipants() }
            .onFailure { println(it.message) }
            .getOrElse { inputParticipants() }
    }

    private fun getParticipants(): List<Name> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val participants = readln().split(",").map { it.trim() }.filter { it.isNotBlank() }
        return when (participants.size) {
            0 -> inputParticipants()
            else -> participants.map { Name(it) }
        }
    }

    fun inputBettingMoney(name: String): Money {
        return runCatching { Money(getBettingMoney(name)) }
            .onFailure { println(it.message) }
            .getOrElse { inputBettingMoney(name) }
    }

    private fun getBettingMoney(name: String): Int {
        println("\n${name}의 배팅 금액은?")
        return readln().toIntOrNull() ?: getBettingMoney(name)
    }

    fun inputDrawMore(name: String): Boolean {
        println("\n${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readln()) {
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
