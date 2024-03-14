package blackjack.model

class Judge(
    val dealer: GameInfo,
    val players: List<GameInfo>,
) {
    fun getDealerResult(): Scoreboard {
        var win = 0
        var draw = 0
        var lose = 0

        players.forEach { playerStat ->
            val dealerDifference = CRITERIA_NUMBER - dealer.sumOfCards
            val playerDifference = CRITERIA_NUMBER - playerStat.sumOfCards

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
        return players.map { playerStat ->
            val dealerDifference = CRITERIA_NUMBER - dealer.sumOfCards
            val playerDifference = CRITERIA_NUMBER - playerStat.sumOfCards

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

    fun getDealerIncome(): Money {
        var currentAmount = Money(0)
        players.forEach { playerStat ->
            val dealerDifference = CRITERIA_NUMBER - dealer.sumOfCards
            val playerDifference = CRITERIA_NUMBER - playerStat.sumOfCards

            when {
                dealerDifference != 0 && playerDifference == 0 -> currentAmount -= (playerStat.moneyAmount) * 1.5
                dealerDifference < 0 && playerDifference >= 0 -> currentAmount -= playerStat.moneyAmount
                playerDifference < 0 && dealerDifference >= 0 -> currentAmount += playerStat.moneyAmount
                playerDifference < dealerDifference -> currentAmount -= playerStat.moneyAmount
                dealerDifference < playerDifference -> currentAmount += playerStat.moneyAmount
                else -> Unit
            }
        }

        return currentAmount
    }

    fun getPlayersIncome(): List<Money> {
        return players.map { playerStat ->
            getSinglePlayerIncome(playerStat)
        }
    }

    private fun getSinglePlayerIncome(playerGameInfo: GameInfo): Money {
        val dealerDifference = CRITERIA_NUMBER - dealer.sumOfCards
        val playerDifference = CRITERIA_NUMBER - playerGameInfo.sumOfCards

        return when {
            dealerDifference != 0 && playerDifference == 0 -> playerGameInfo.moneyAmount * 1.5
            dealerDifference < 0 && playerDifference >= 0 -> playerGameInfo.moneyAmount
            playerDifference < 0 && dealerDifference >= 0 -> -playerGameInfo.moneyAmount
            playerDifference < dealerDifference -> playerGameInfo.moneyAmount
            dealerDifference < playerDifference -> -playerGameInfo.moneyAmount
            else -> Money(0)
        }
    }

    companion object {
        private const val CRITERIA_NUMBER = 21
        private const val RESULT_WIN = "승"
        private const val RESULT_DRAW = "무"
        private const val RESULT_LOSE = "패"
    }
}
