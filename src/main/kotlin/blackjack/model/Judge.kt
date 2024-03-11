package blackjack.model

class Judge(
    val dealer: Dealer,
    val players: Players,
) {
    fun getDealerResult(): Scoreboard {
        var win = 0
        var draw = 0
        var lose = 0

        players.value.forEach { player ->
            val dealerDifference = CRITERIA_NUMBER - dealer.gameInfo.sumOfCards
            val playerDifference = CRITERIA_NUMBER - player.gameInfo.sumOfCards

            when {
                dealerDifference < 0 && playerDifference < 0 -> draw++
                dealerDifference < 0 -> lose++
                playerDifference < 0 -> win++
                playerDifference < dealerDifference -> lose++
                dealerDifference < playerDifference -> win++
                else -> draw++
            }
        }

        return Scoreboard(win, draw, lose)
    }

    fun getPlayerResults(): List<String> {
        return players.value.map { player ->
            val dealerDifference = CRITERIA_NUMBER - dealer.gameInfo.sumOfCards
            val playerDifference = CRITERIA_NUMBER - player.gameInfo.sumOfCards

            when {
                dealerDifference < 0 && playerDifference < 0 -> RESULT_DRAW
                dealerDifference < 0 -> RESULT_WIN
                playerDifference < 0 -> RESULT_LOSE
                playerDifference < dealerDifference -> RESULT_WIN
                dealerDifference < playerDifference -> RESULT_LOSE
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
