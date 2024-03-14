package blackjack.model

class Judge(
    val dealer: GameInfo,
    val players: List<GameInfo>,
) {
    fun getDealerIncome(): Money {
        var currentAmount = Money(0)
        players.forEach { playerStat ->
            val dealerDifference = CRITERIA_NUMBER - dealer.sumOfCards
            val playerDifference = CRITERIA_NUMBER - playerStat.sumOfCards

            when {
                dealerDifference != 0 && playerDifference == 0 -> currentAmount -= (playerStat.moneyAmount) * BLACKJACK_BONUS_MULTIPLIER
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
            dealerDifference != 0 && playerDifference == 0 -> playerGameInfo.moneyAmount * BLACKJACK_BONUS_MULTIPLIER
            dealerDifference < 0 && playerDifference >= 0 -> playerGameInfo.moneyAmount
            playerDifference < 0 && dealerDifference >= 0 -> -playerGameInfo.moneyAmount
            playerDifference < dealerDifference -> playerGameInfo.moneyAmount
            dealerDifference < playerDifference -> -playerGameInfo.moneyAmount
            else -> Money(0)
        }
    }

    companion object {
        private const val CRITERIA_NUMBER = 21
        private const val BLACKJACK_BONUS_MULTIPLIER = 1.5
    }
}
