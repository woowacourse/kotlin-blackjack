package view

import domain.person.Dealer
import domain.person.Player

object OnboardingView {
    private const val ERROR_INPUT_BLACK = "공백은 입력할 수 없습니다."
    private const val NAME_INPUT_SCRIPT = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val SHARE_TWO_CARDS_SCRIPT = "%s와 %s에게 2장의 카드를 나누었습니다."
    private const val INITIAL_CARDS_SCRIPT = "%s 카드: %s"

    fun requestInputNames(): List<String> {
        println(NAME_INPUT_SCRIPT)
        val input = readln()
        if (input.isBlank()) {
            println(ERROR_INPUT_BLACK)
            return requestInputNames()
        }
        return input.split(',').map { it.trim() }
    }

    fun printInitialSetting(players: List<Player>, dealer: Dealer) {
        println()
        println(SHARE_TWO_CARDS_SCRIPT.format(dealer.name, players.joinToString(", ") { it.name }))
        println(INITIAL_CARDS_SCRIPT.format(dealer.name, dealer.showFirstCard().joinToString { it.toString() }))
        players.forEach { printInitialCards(it) }
        println()
    }

    private fun printInitialCards(player: Player) {
        println(INITIAL_CARDS_SCRIPT.format(player.name, player.showHandOfCards().joinToString(",") { it.toString() }))
    }
}
