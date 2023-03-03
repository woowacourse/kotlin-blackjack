package view

import domain.person.Dealer
import domain.person.Player

object OnboardingView {
    private const val ERROR_INPUT_BLACK = "공백은 입력할 수 없습니다."
    fun requestInputNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readln()
        if (input.isBlank()) {
            println(ERROR_INPUT_BLACK)
            return requestInputNames()
        }
        return input.split(',').map { it.trim() }
    }

    fun printInitialSetting(players: List<Player>, dealer: Dealer) {
        println()
        println(
            "${dealer.name}와 " +
                players.joinToString(", ") { it.name } +
                "에게 2장의 카드를 나누었습니다."
        )
        println("${dealer.name}: ${dealer.showOneCard().joinToString { it.toString() }}")
        players.forEach { printInitialCards(it) }
        println()
    }

    private fun printInitialCards(player: Player) {
        println("${player.name}카드: " + player.cards.joinToString(",") { it.toString() })
    }
}
