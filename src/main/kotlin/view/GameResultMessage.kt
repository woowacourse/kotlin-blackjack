package view

import model.GameResult

object GameResultMessage {
    private val messages =
        mapOf(
            GameResult.WIN to "승",
            GameResult.LOSE to "패",
            GameResult.DRAW to "무",
        )

    fun getGameResultMessage(gameResult: GameResult): String {
        return messages[gameResult] ?: ""
    }
}
