package view

import entity.Cards
import entity.Dealer
import entity.Player
import model.BlackjackStage

class GameView {
    private fun printDealerStatus(dealer: Dealer) {
        print(MESSAGE_DEALER_NAME)
        println(ViewUtils.cardsToString(dealer.cards.value.take(1).let { Cards(it) }))
    }

    fun printPlayerStatus(player: Player) {
        print(player.name.value)
        print(MESSAGE_PLAYER_NAME)
        println(ViewUtils.cardsToString(player.cards))
    }

    fun printInitialUsersStatus(blackjackStage: BlackjackStage) {
        println(
            MESSAGE_USERS_STATUS.format(
                blackjackStage.players.value.joinToString(", ") {
                    it.name.value
                }
            )
        )
        printDealerStatus(blackjackStage.dealer)
        blackjackStage.players.value.forEach { printPlayerStatus(it) }
    }

    fun printWhetherMoreCard(name: String) {
        println(MESSAGE_MORE_CARD.format(name))
    }

    fun readWhetherMoreCard(): Boolean {
        val input = readln().trim()
        require(input == "y" || input == "n") {
            MESSAGE_CONDITION_ERROR.format(input)
        }
        return input == "y"
    }

    fun printDealerMoreCard() {
        println(MESSAGE_DEALER_MORE_CARD)
    }

    companion object {
        private const val MESSAGE_DEALER_NAME = "딜러: "
        private const val MESSAGE_PLAYER_NAME = "카드: "
        private const val MESSAGE_USERS_STATUS = "딜러와 %s에게 2장의 나누었습니다."
        private const val MESSAGE_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val MESSAGE_DEALER_MORE_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_CONDITION_ERROR = "y나 n을 입력하여야 합니다. 입력된 값 : %s"
    }
}
