package entity.result

import entity.users.Dealer
import entity.users.Player
import entity.users.Users
import misc.GameRule

class UserBettingResult(val users: Users, private val playersGameResult: PlayersGameResult) {
    val playersBettingResults = mutableMapOf<Player, Double>()

    fun getBettingResults(): MutableMap<Player, Double> {
        users.players.value.forEach { player ->
            playersBettingResults[player] = getBettingResult(player, users.dealer)
        }
        return playersBettingResults
    }

    private fun getBettingResult(player: Player, dealer: Dealer): Double {
        val playerBettingMoney = player.userInformation.bettingMoney.value.toDouble()
        val playerCardsSum = player.userInformation.cards.sumOfNumbers()
        val dealerCardSum = dealer.userInformation.cards.sumOfNumbers()
        val playerGameResult = playersGameResult.value[player]
        return when {
            playerCardsSum == GameRule.WINNING_NUMBER && player.userInformation.cards.value.size == PLAYER_BLACKJACK_CARD_SIZE -> playerBettingMoney * PLAYER_BLACKJACK_BETTING
            playerGameResult == GameResultType.WIN || dealerCardSum > GameRule.WINNING_NUMBER -> playerBettingMoney * PLAYER_WIN_OR_DEALER_OVER_TWENTY_ONE_BETTING
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
        private const val PLAYER_BLACKJACK_CARD_SIZE = 2
        private const val PLAYER_BLACKJACK_BETTING = 2.5
        private const val PLAYER_WIN_OR_DEALER_OVER_TWENTY_ONE_BETTING = 2
    }
}
