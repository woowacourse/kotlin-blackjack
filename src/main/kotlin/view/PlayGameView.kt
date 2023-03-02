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

    fun printUserCard(user: User) {
        println(USERS_CARD.format(user.name, (user.cards.map { it.toString() }).joinToString(SEPARATOR)))
    }

    fun requestOneMoreCard(user: User): String {
        println(REQUEST_MORE_CARD.format(user.name))
        return readLine() ?: requestOneMoreCard(user)
    }

    fun printDealerPickNewCard() = println(NOTICE_DEALER_PICK_NEW_CARD)

    companion object {
        private const val NOTICE_DEALER_PICK_NEW_CARD = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val NOTICE_SPLIT_CARD = "딜러와 %s에게 2장의 카드를 나누었습니다."
        private const val DEALER_CARD = "딜러: %s"
        private const val USERS_CARD = "%s: %s"
        private const val SEPARATOR = ", "
        private const val REQUEST_MORE_CARD = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    }
}
