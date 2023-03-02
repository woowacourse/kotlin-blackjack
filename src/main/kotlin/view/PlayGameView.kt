package view

import domain.Dealer
import domain.User

class PlayGameView {

    fun printNoticeSplitCard(userNames: List<String>) {
        val names = userNames.joinToString(SEPARATOR)
        println(NOTICE_SPLIT_CARD.format(names))
    }

    fun printPlayerCard(dealer: Dealer, users: List<User>) {
        println(DEALER_CARD.format(dealer.cards.first()))
        users.forEach { user ->
            println(USERS_CARD.format(user.name, (user.cards.map { it.toString() }).joinToString(SEPARATOR)))
        }
    }

    companion object {
        private const val NOTICE_SPLIT_CARD = "딜러와 %s에게 2장의 카드를 나누었습니다."
        private const val DEALER_CARD = "딜러: %s"
        private const val USERS_CARD = "%s: %s"
        private const val SEPARATOR = ", "
    }
}
