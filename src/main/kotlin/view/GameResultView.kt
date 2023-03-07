package view

import domain.GameResult
import domain.Players

class GameResultView {

    fun printCardResult(players: Players) {
        val dealer = players.dealer
        val users = players.users
        println(
            PLAYER_CARD.format(
                dealer.name,
                dealer.cards.value.joinToString(SEPARATOR) { it.toString() },
                dealer.cards.actualCardValueSum(),
            ),
        )
        users.forEach { user ->
            println(
                PLAYER_CARD.format(
                    user.name,
                    (user.cards.value.map { it.toString() }).joinToString(
                        SEPARATOR,
                    ),
                    user.cards.actualCardValueSum(),
                ),
            )
        }
    }

    fun printFinalResult(gameResult: List<GameResult>, players: Players) {
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
        players.users.forEachIndexed { index, user ->
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
