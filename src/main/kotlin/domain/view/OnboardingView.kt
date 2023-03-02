package domain.view

import domain.person.Dealer
import domain.person.Player

class OnboardingView {

    fun requestInputNames(): List<String> {
        val input = readln()
        if (input.isBlank()) {
            println(ERROR_INPUT_BLACK)
            requestInputNames()
        }
        return input.split(',').map { it.trim() }
    }

    fun printInitialSetting(players: List<Player>, dealer: Dealer) {
        println(
            "${dealer.name}와 " +
                players.joinToString(", ") { it.name } +
                "에게 2장의 카드를 나누었습니다."
        )
        println("${dealer.name}: ${dealer.showOneCard().joinToString { it.toString() }}")
        players.forEach { printInitialCards(it) }
    }

    private fun printInitialCards(player: Player) {
        println("${player.name}카드: " + player.cards.joinToString(",") { it.toString() })
    }

    companion object {
        const val ERROR_INPUT_BLACK = "공백은 입력할 수 없습니다."
    }
}
