package view

import domain.Dealer
import domain.GameResult
import domain.User

class GameResultView {

    fun printCardResult(dealer: Dealer, users: List<User>) {
        println(
            PLAYER_CARD.format(
                dealer.name,
                dealer.cards.map { it.toString() }.joinToString(SEPARATOR),
                dealer.actualCardValueSum(),
            ),
        )
        users.forEach { user ->
            println(
                PLAYER_CARD.format(
                    user.name,
                    (user.cards.map { it.toString() }).joinToString(
                        SEPARATOR,
                    ),
                    user.actualCardValueSum(),
                ),
            )
        }
    }

    fun printFinalResult(gameResult: List<GameResult>, users: List<User>) {
        println(FINAL_RESULT)
        println(
            DEALER_RESULT_FORMAT.format(
                gameResult.count { it == GameResult.LOSE },
                GameResult.WIN.label,
                gameResult.count { it == GameResult.DRAW },
                GameResult.DRAW.label,
                gameResult.count { it == GameResult.WIN },
                GameResult.LOSE.label,
            ),
        )
        users.forEachIndexed { index, user ->
            println(USER_RESULT_FORMAT.format(user.name, gameResult[index].label))
        }
    }

    companion object {
        private const val DEALER_RESULT_FORMAT = "딜러: %d%s %d%s %d%s"
        private const val USER_RESULT_FORMAT = "%s: %s"
        private const val PLAYER_CARD = "%s: %s - 결과: %d"
        private const val SEPARATOR = ", "
        private const val FINAL_RESULT = "## 최종 승패"
    }
}
