package blackjack.view

import java.lang.IllegalArgumentException

object InputView {
    fun getPlayerNames(): List<String> {
        println(REQUEST_PLAYERS_NAME)
        val input = readln()
        return input.split(",").map { it.trim() }
    }

    fun askPlayerHit(name: String): Boolean {
        println(requestAdditionalCard(name))
        val input = readln()
        if (input == YES) return true
        if (input == NO) return false
        throw IllegalArgumentException()
    }

    private const val REQUEST_PLAYERS_NAME = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val YES = "y"
    private const val NO = "n"

    private fun requestAdditionalCard(name: String): String = "${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
}
