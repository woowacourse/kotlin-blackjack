package view

import domain.GameResult
import domain.Players
import domain.User
import domain.UserProfit

class GameResultView {

    fun printCardResult(players: Players) {
        val dealer = players.dealer
        val users = players.users
        println(
            PLAYER_CARD.format(
                dealer.name,
                dealer.cards.value.joinToString(SEPARATOR) { it.toString() },
                dealer.cards.score.value,
            ),
        )
        printUserCards(users)
    }

    private fun printUserCards(users: List<User>) {
        users.forEach { user ->
            println(
                PLAYER_CARD.format(
                    user.name,
                    (user.cards.value.map { it.toString() }).joinToString(
                        SEPARATOR,
                    ),
                    user.getScore().value,
                ),
            )
        }
    }

    fun printFinalResult(users: List<User>) {
        println(FINAL_RESULT)
        printDealerResult(users)
        users.forEach { user ->
            println(USER_RESULT_FORMAT.format(user.name, user.gameResult.label))
        }
    }

    private fun printDealerResult(users: List<User>) {
        val loseCount = users.count { it.gameResult == GameResult.LOSE }
        val drawCount = users.count { it.gameResult == GameResult.DRAW }
        val winCount = users.count { it.gameResult == GameResult.WIN }
        println(
            DEALER_RESULT_FORMAT.format(
                loseCount, GameResult.WIN.label,
                drawCount, GameResult.DRAW.label,
                winCount, GameResult.LOSE.label,
            ),
        )
    }

    fun printFinalProfit(dealerProfit: Double, usersProfit: List<UserProfit>) {
        println(FINAL_PROFIT_TITLE)
        println(DEALER_PROFIT.format(dealerProfit.toInt()))
        usersProfit.forEach { nameAndProfit ->
            println(USER_RESULT_FORMAT.format(nameAndProfit.user.name, nameAndProfit.profit.toInt()))
        }
    }

    companion object {
        private const val DEALER_RESULT_FORMAT = "딜러: %d%s %d%s %d%s"
        private const val USER_RESULT_FORMAT = "%s: %s"
        private const val PLAYER_CARD = "%s: %s - 결과: %d"
        private const val SEPARATOR = ", "
        private const val FINAL_RESULT = "## 최종 승패"
        private const val FINAL_PROFIT_TITLE = "## 최종 수익"
        private const val DEALER_PROFIT = "딜러: %s"
    }
}
