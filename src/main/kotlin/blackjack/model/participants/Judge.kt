package blackjack.model.participants

import blackjack.model.gameInfo.EarningRate
import blackjack.model.gameInfo.GameInfo
import blackjack.model.gameInfo.Money

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
    ): Boolean = playerDifference < BLACKJACK_DIFFERENCE && dealerDifference >= BLACKJACK_DIFFERENCE

    private fun isPlayerBlackjack(
        playerGameInfo: GameInfo,
        dealerDifference: Int,
    ): Boolean =
        playerGameInfo.cards.take(INITIAL_CARD_SIZE).sumOf { it.value } == CRITERIA_NUMBER &&
            dealerDifference != BLACKJACK_DIFFERENCE

    private fun isPlayerWin(
        playerDifference: Int,
        dealerDifference: Int,
    ): Boolean =
        dealerDifference != BLACKJACK_DIFFERENCE && playerDifference == BLACKJACK_DIFFERENCE ||
            dealerDifference < BLACKJACK_DIFFERENCE && playerDifference >= BLACKJACK_DIFFERENCE ||
            playerDifference < dealerDifference

    private fun isPlayerLose(
        playerDifference: Int,
        dealerDifference: Int,
    ): Boolean =
        playerDifference < BLACKJACK_DIFFERENCE && dealerDifference >= BLACKJACK_DIFFERENCE ||
            dealerDifference < playerDifference

    companion object {
        private const val BLACKJACK_DIFFERENCE = 0
        private const val INITIAL_CARD_SIZE = 2
        private const val CRITERIA_NUMBER = 21
    }
}
