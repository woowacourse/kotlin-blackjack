package view

import domain.Dealer
import domain.GameResult
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

    fun printFinalResult(gameResult: List<GameResult>, users: List<User>) {
        println(FINAL_RESULT)
        println(
            "딜러: ${gameResult.count { it == GameResult.LOSE }}승 " +
                "${gameResult.count { it == GameResult.DRAW }}무 " +
                "${gameResult.count { it == GameResult.WIN }}패",
        )
        users.forEachIndexed { result, user ->
            println("${user.name}: ${gameResult[result].label}")
        }
    }

    companion object {
        private const val DEALER_CARD = "딜러: %s - 결과: %d"
        private const val USERS_CARD = "%s: %s - 결과: %d"
        private const val SEPARATOR = ", "
        private const val FINAL_RESULT = "## 최종 승패"
        private const val WIN = "승"
        private const val LOSE = "패"
        private const val DRAW = "무"
    }
}
