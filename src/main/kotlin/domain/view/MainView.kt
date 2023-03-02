package domain.view

import domain.constant.DEALER_STAND_CONDITION
import domain.person.Player

object MainView {
    fun requestPlayerDecision(player: Player): String {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val input = readln().trim()
        if (input !in setOf("y", "n")) return requestPlayerDecision(player)
        return input
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: " + player.cards.joinToString(",") { it.toString() })
    }

    fun printDealerGetMoreCard() {
        println("딜러는 ${DEALER_STAND_CONDITION}이하라 한장의 카드를 더 받았습니다.")
    }

    fun printDealerNoMoreCard() {
        println("딜러는 ${DEALER_STAND_CONDITION}초과라 한장의 카드를 받지 않습니다.")
    }
}
