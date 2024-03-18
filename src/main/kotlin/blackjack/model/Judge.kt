package blackjack.model

class Judge(
    val dealer: GameInfo,
    val players: List<GameInfo>,
) {
    fun getDealerIncome(): Money {
        val playersIncome = getPlayersIncome().sumOf { it.amount }
        return -Money(playersIncome)
    }

    fun getPlayersIncome(): List<Money> {
        return players.map { playerStat ->
            getSinglePlayerIncome(playerStat)
        }
    }

    private fun getSinglePlayerIncome(playerGameInfo: GameInfo): Money {
        val dealerDifference = CRITERIA_NUMBER - dealer.sumOfCards
        val playerDifference = CRITERIA_NUMBER - playerGameInfo.sumOfCards
        val earningRate = getEarningRate(dealerDifference, playerDifference, playerGameInfo).rate
        return playerGameInfo.moneyAmount * earningRate
    }

    private fun getEarningRate(
        dealerDifference: Int,
        playerDifference: Int,
        playerGameInfo: GameInfo,
    ): EarningRate {
        return when {
            isPlayerBurst(playerDifference, dealerDifference) -> EarningRate.BURST
            isPlayerBlackjack(playerGameInfo, dealerDifference) -> EarningRate.BLACKJACK
            isPlayerWin(playerDifference, dealerDifference) -> EarningRate.WIN
            isPlayerLose(playerDifference, dealerDifference) -> EarningRate.LOSE
            else -> EarningRate.DRAW
        }
    }

    private fun isPlayerBurst(
        playerDifference: Int,
        dealerDifference: Int,
    ): Boolean = playerDifference < 0 && dealerDifference >= 0

    private fun isPlayerBlackjack(
        playerGameInfo: GameInfo,
        dealerDifference: Int,
    ): Boolean =
        playerGameInfo.cards.take(INITIAL_CARD_SIZE).sumOf { it.value } == CRITERIA_NUMBER &&
            dealerDifference != 0

    private fun isPlayerWin(
        playerDifference: Int,
        dealerDifference: Int,
    ): Boolean =
        dealerDifference != 0 && playerDifference == 0 ||
            dealerDifference < 0 && playerDifference >= 0 ||
            playerDifference < dealerDifference

    private fun isPlayerLose(
        playerDifference: Int,
        dealerDifference: Int,
    ): Boolean =
        playerDifference < 0 && dealerDifference >= 0 ||
            dealerDifference < playerDifference

    companion object {
        private const val INITIAL_CARD_SIZE = 2
        private const val CRITERIA_NUMBER = 21
    }
}
