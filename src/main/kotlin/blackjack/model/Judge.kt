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

        return when {
            isPlayerBlackjack(playerGameInfo) && dealerDifference != 0 ->
                playerGameInfo.moneyAmount * BLACKJACK_BONUS_MULTIPLIER
            dealerDifference != 0 && playerDifference == 0 -> playerGameInfo.moneyAmount
            dealerDifference < 0 && playerDifference >= 0 -> playerGameInfo.moneyAmount
            playerDifference < 0 && dealerDifference >= 0 -> -playerGameInfo.moneyAmount
            playerDifference < dealerDifference -> playerGameInfo.moneyAmount
            dealerDifference < playerDifference -> -playerGameInfo.moneyAmount
            else -> Money(0)
        }
    }

    private fun isPlayerBlackjack(playerGameInfo: GameInfo): Boolean =
        playerGameInfo.cards.take(INITIAL_CARD_SIZE).sumOf { it.value } == CRITERIA_NUMBER

    companion object {
        private const val INITIAL_CARD_SIZE = 2
        private const val CRITERIA_NUMBER = 21
        private const val BLACKJACK_BONUS_MULTIPLIER = 1.5
    }
}
