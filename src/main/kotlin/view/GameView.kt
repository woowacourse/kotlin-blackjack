package view

import entity.card.Cards
import entity.users.Dealer
import entity.users.Player
import entity.users.Players
import view.ViewUtils.Companion.isString

class GameView {
    private fun printDealerStatus(dealer: Dealer) {
        print(MESSAGE_DEALER_NAME)
        println(dealer.gameInformation.cards.value.take(1).let { Cards(it) }.isString())
    }

    fun printPlayerStatus(player: Player) {
        print(player.name.value)
        print(MESSAGE_PLAYER_NAME)
        println(player.gameInformation.cards.isString())
    }

    fun printInitStatus(dealer: Dealer, players: Players) {
        println(
            MESSAGE_USERS_STATUS.format(
                players.value.joinToString(", ") {
                    it.name.value
                }
            )
        )
        printDealerStatus(dealer)
        players.value.forEach { printPlayerStatus(it) }
    }

    fun printMoreString1(name: String) = println(MESSAGE_MORE_CARD.format(name))

    fun getMoreString() = readln().trim()

    fun printMoreString2() = println(MESSAGE_DEALER_MORE_CARD)

    companion object {
        private const val MESSAGE_DEALER_NAME = "딜러: "
        private const val MESSAGE_PLAYER_NAME = "카드: "
        private const val MESSAGE_USERS_STATUS = "딜러와 %s에게 2장의 나누었습니다."
        private const val MESSAGE_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        private const val MESSAGE_DEALER_MORE_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다.\n"
    }
}
