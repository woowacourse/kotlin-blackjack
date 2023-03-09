package entity.result

import entity.users.Dealer
import entity.users.Player
import entity.users.Players
import misc.GameRule

class UserBettingResult {
    val playersBettingResults = mutableMapOf<Player, Double>()

    fun getPlayersBettingResults(players: Players, dealer: Dealer, playersGameResult: PlayersGameResult): MutableMap<Player, Double> {
        players.value.forEach { player ->
            playersBettingResults[player] = getPlayerBettingResult(player, dealer, playersGameResult)
        }
        return playersBettingResults
    }

    private fun getPlayerBettingResult(player: Player, dealer: Dealer, playersGameResult: PlayersGameResult): Double {
        val playerBettingMoney = player.userInformation.bettingMoney.value.toDouble()
        if (player.cards.sumOfNumbers() == GameRule.WINNING_NUMBER && player.cards.value.size == 2) return playerBettingMoney + playerBettingMoney * 1.5
        if (playersGameResult.value[player] == GameResultType.WIN || dealer.cards.sumOfNumbers() > GameRule.WINNING_NUMBER) return 2 * playerBettingMoney
        if (player.cards.sumOfNumbers() == GameRule.WINNING_NUMBER && dealer.cards.sumOfNumbers() == GameRule.WINNING_NUMBER) return playerBettingMoney
        if (player.cards.sumOfNumbers() > GameRule.WINNING_NUMBER) return -playerBettingMoney
        if (playersGameResult.value[player] == GameResultType.LOSE) return -playerBettingMoney
        return INIT_PROFIT_MONEY
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
