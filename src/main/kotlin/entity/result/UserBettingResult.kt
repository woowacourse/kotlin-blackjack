package entity.result

import entity.users.Dealer
import entity.users.Player
import entity.users.Users
import misc.GameRule

class UserBettingResult {
    val playersBettingResults = mutableMapOf<Player, Double>()

    fun getPlayersBettingResults(users: Users, playersGameResult: PlayersGameResult): MutableMap<Player, Double> {
        users.players.value.forEach { player ->
            playersBettingResults[player] = getPlayerBettingResult(player, users.dealer, playersGameResult)
        }
        return playersBettingResults
    }

    private fun getPlayerBettingResult(player: Player, dealer: Dealer, playersGameResult: PlayersGameResult): Double {
        val playerBettingMoney = player.userInformation.bettingMoney.value.toDouble()
        val playerCardsSum = player.cards.sumOfNumbers()
        val dealerCardSum = dealer.cards.sumOfNumbers()
        val playerGameResult = playersGameResult.value[player]
        return when {
            playerCardsSum == GameRule.WINNING_NUMBER && player.cards.value.size == 2 -> playerBettingMoney + playerBettingMoney * 1.5
            playerGameResult == GameResultType.WIN || dealerCardSum > GameRule.WINNING_NUMBER -> 2 * playerBettingMoney
            playerCardsSum == GameRule.WINNING_NUMBER && dealerCardSum == GameRule.WINNING_NUMBER -> playerBettingMoney
            playerCardsSum > GameRule.WINNING_NUMBER || playerGameResult == GameResultType.LOSE -> -playerBettingMoney
            else -> INIT_PROFIT_MONEY
        }
    }

    fun getDealerBettingResult(): Double {
        var dealerProfitMoney = INIT_PROFIT_MONEY
        playersBettingResults.forEach { (_, profitMoney) ->
            dealerProfitMoney += profitMoney
        }
        dealerProfitMoney = -dealerProfitMoney
        return dealerProfitMoney
    }

    companion object {
        private const val INIT_PROFIT_MONEY = 0.0
    }
}
