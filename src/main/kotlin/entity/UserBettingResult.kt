package entity

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
        if (player.cards.sumOfNumbers() == 21 && player.cards.value.size == 2) return playerBettingMoney + playerBettingMoney * 1.5
        if (playersGameResult.value[player] == GameResultType.WIN || dealer.cards.sumOfNumbers() > 21) return 2 * playerBettingMoney
        if (player.cards.sumOfNumbers() == 21 && dealer.cards.sumOfNumbers() == 21) return playerBettingMoney
        if (player.cards.sumOfNumbers() > 21) return -playerBettingMoney
        if (playersGameResult.value[player] == GameResultType.LOSE) return -playerBettingMoney
        return 0.0
    }

    fun getDealerBettingResult(): Double {
        var dealerProfitMoney = 0.0
        playersBettingResults.forEach { (_, profitMoney) ->
            dealerProfitMoney += profitMoney
        }
        dealerProfitMoney = -dealerProfitMoney
        return dealerProfitMoney
    }
}
