package view

import domain.player.Dealer
import domain.player.Player
import domain.player.User
import view.util.toCardInfo

class PlayGameView {

    fun printNoticeSplitCard(userNames: List<String>) {
        val names = userNames.joinToString(SEPARATOR)
        println(NOTICE_SPLIT_CARD.format(names))
    }

    fun printPlayerCard(dealer: Dealer, users: List<User>) {
        println(USER_RESULT_FORMAT.format(dealer.name, dealer.cards.first().toCardInfo()))
        users.forEach { user ->
            println(formatPlayerCard(user))
        }
    }

    fun printUserCard(user: User) {
        println(formatPlayerCard(user))
    }

    fun requestOneMoreCard(user: User): String {
        println(REQUEST_MORE_CARD.format(user.name))
        return readLine() ?: requestOneMoreCard(user)
    }

    fun printDealerPickNewCard() = println(NOTICE_DEALER_PICK_NEW_CARD)

    private fun formatPlayerCard(player: Player) =
        USER_RESULT_FORMAT.format(
            player.name,
            player.cards.joinToString(SEPARATOR) { card ->
                card.toCardInfo()
            },
        )

    companion object {
        private const val NOTICE_DEALER_PICK_NEW_CARD = "\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n"
        private const val NOTICE_SPLIT_CARD = "딜러와 %s에게 2장의 카드를 나누었습니다."
        private const val USER_RESULT_FORMAT = "%s: %s"
        private const val SEPARATOR = ", "
        private const val REQUEST_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    }
}
