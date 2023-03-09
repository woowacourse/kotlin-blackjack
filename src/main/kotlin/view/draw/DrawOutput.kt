package view.draw

import constant.BlackJackConstants
import domain.person.Player
import view.ViewCommon.toText

object DrawOutput {
    private const val ONE_MORE_CARD_SCRIPT = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    private const val INITIAL_CARDS_SCRIPT = "%s 카드: %s"
    private const val SEPARATOR = ","
    private const val DEALER_ONE_MORE_CARD_SCRIPT =
        "딜러는 ${BlackJackConstants.DEALER_STAND_CONDITION}이하라 한장의 카드를 더 받았습니다."
    private const val DEALER_NO_MORE_CARD_SCRIPT =
        "딜러는 ${BlackJackConstants.DEALER_STAND_CONDITION}초과라 한장의 카드를 받지 않습니다."

    fun printRequestDecision(name: String) {
        println(ONE_MORE_CARD_SCRIPT.format(name))
    }

    fun printInitialCards(player: Player) {
        println(
            INITIAL_CARDS_SCRIPT.format(
                player.name,
                player.showHandOfCards().joinToString(SEPARATOR) { it.toText() },
            ),
        )
    }

    fun printDealerOneMoreCard() {
        println(DEALER_ONE_MORE_CARD_SCRIPT)
    }

    fun printDealerNoMoreCard() {
        println(DEALER_NO_MORE_CARD_SCRIPT)
    }
}
