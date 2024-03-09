package blackjack.model

class Judge(
    val dealerInfo: GameInfo,
    val playersInfo: List<GameInfo>,
) {
    fun getDealerResult(): Scoreboard {
        var win = 0
        var draw = 0
        var lose = 0

        val dealerTotal = dealerInfo.calculateTotal()

        playersInfo.forEach { playerStat ->
            val playerTotal = playerStat.calculateTotal()

            when {
                dealerTotal > CRITERIA_NUMBER && playerTotal > CRITERIA_NUMBER -> draw++
                dealerTotal > CRITERIA_NUMBER -> lose++
                playerTotal > CRITERIA_NUMBER -> win++
                playerTotal > dealerTotal -> lose++
                dealerTotal > playerTotal -> win++
                else -> draw++
            }
        }

        return Scoreboard(win, draw, lose)
    }

    fun getPlayerResults(): List<String> {
        val dealerTotal = dealerInfo.calculateTotal()

        return playersInfo.map { playerStat ->
            val playerTotal = playerStat.calculateTotal()

            when {
                dealerTotal > CRITERIA_NUMBER && playerTotal > CRITERIA_NUMBER -> RESULT_DRAW
                dealerTotal > CRITERIA_NUMBER -> RESULT_WIN
                playerTotal > CRITERIA_NUMBER -> RESULT_LOSE
                playerTotal > dealerTotal -> RESULT_WIN
                dealerTotal > playerTotal -> RESULT_LOSE
                else -> RESULT_DRAW
            }
        }
    }

    companion object {
        private const val CRITERIA_NUMBER = 21
        private const val RESULT_WIN = "승"
        private const val RESULT_DRAW = "무"
        private const val RESULT_LOSE = "패"
    }
}
