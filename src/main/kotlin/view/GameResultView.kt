package view

import domain.Dealer
import domain.User

class GameResultView {

    fun printCardResult(dealer: Dealer, users: List<User>) {
        println(DEALER_CARD.format(dealer.cards.map { it.toString() }.joinToString(SEPARATOR), dealer.validDealerSum()))
        users.forEach { user ->
            println(
                USERS_CARD.format(
                    user.name,
                    (user.cards.map { it.toString() }).joinToString(
                        SEPARATOR,
                    ),
                    user.validUserSum(),
                ),
            )
        }
    }

    companion object {
        private const val DEALER_CARD = "딜러: %s - 결과: %d"
        private const val USERS_CARD = "%s: %s - 결과: %d"
        private const val SEPARATOR = ", "
    }
}
