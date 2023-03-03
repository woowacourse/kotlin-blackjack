package view

import domain.constant.DEALER_STAND_CONDITION
import domain.constant.Decision
import domain.person.Player

object MainView {
    fun requestPlayerDecision(name: String): String {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readln().trim()
        if (!Decision.has(input)) return requestPlayerDecision(name)
        return input
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: " + player.cards.joinToString(",") { it.toString() })
    }

    fun printDealerGetMoreCard() {
        println("딜러는 ${DEALER_STAND_CONDITION}이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    fun printDealerNoMoreCard() {
        println("딜러는 ${DEALER_STAND_CONDITION}초과라 한장의 카드를 받지 않습니다.")
        println()
    }
}
