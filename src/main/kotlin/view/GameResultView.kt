package view

import domain.Dealer
import domain.GameResult
import domain.Player
import domain.User

class GameResultView {

    fun printCardResult(dealer: Dealer, users: List<User>) {
        println(formatPlayerCard(dealer))
        users.forEach { user ->
            println(formatPlayerCard(user))
        }
    }

    fun printFinalResult(gameResult: List<GameResult>, users: List<User>) {
        println(FINAL_RESULT)
        println(formatDealerResult(gameResult))
        users.forEachIndexed { index, user ->
            println(USER_RESULT_FORMAT.format(user.name, gameResult[index].label))
        }
    }

    private fun formatPlayerCard(player: Player) =
        PLAYER_CARD_RESULT.format(
            player.name,
            player.cards.joinToString(SEPARATOR) { card ->
                card.toString()
            },
            player.validPlayerSum(),
        )

    private fun formatDealerResult(gameResult: List<GameResult>) = DEALER_RESULT_FORMAT.format(
        gameResult.count { it == GameResult.LOSE },
        GameResult.WIN.label,
        gameResult.count { it == GameResult.DRAW },
        GameResult.DRAW.label,
        gameResult.count { it == GameResult.WIN },
        GameResult.LOSE.label,
    )

    companion object {
        private const val DEALER_RESULT_FORMAT = "딜러: %d%s %d%s %d%s"
        private const val USER_RESULT_FORMAT = "%s: %s"
        private const val PLAYER_CARD_RESULT = "%s: %s - 결과: %d"
        private const val SEPARATOR = ", "
        private const val FINAL_RESULT = "\n## 최종 승패"
    }
}
