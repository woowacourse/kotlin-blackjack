package view

import domain.constant.DEALER_STAND_CONDITION
import domain.constant.Decision
import domain.person.Player

object AdditionalCardView {
    private const val ONE_MORE_CARD_SCRIPT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val INITIAL_CARDS_SCRIPT = "%s 카드: %s"
    private const val DEALER_ONE_MORE_CARD_SCRIPT = "딜러는 ${DEALER_STAND_CONDITION}이하라 한장의 카드를 더 받았습니다."
    private const val DEALER_NO_MORE_CARD_SCRIPT = "딜러는 ${DEALER_STAND_CONDITION}초과라 한장의 카드를 받지 않습니다."
    fun requestPlayerDecision(name: String): String {
        println(ONE_MORE_CARD_SCRIPT.format(name))
        val input = readln().trim()
        if (Decision.of(input) != null) return requestPlayerDecision(name)
        return input
    }

    fun printPlayerCards(player: Player) {
        println(INITIAL_CARDS_SCRIPT.format(player.name, player.showHandOfCards().joinToString(",") { it.toString() }))
    }

    fun printDealerGetMoreCard() {
        println(DEALER_ONE_MORE_CARD_SCRIPT)
        println()
    }

    fun printDealerNoMoreCard() {
        println(DEALER_NO_MORE_CARD_SCRIPT)
        println()
    }
}
