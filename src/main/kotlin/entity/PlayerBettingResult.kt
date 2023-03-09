package entity

class PlayerBettingResult() {
    val playersBettingResult = mutableMapOf<Player, Double>()

    fun getPlayersBettingResult(players: Players, dealer: Dealer, playersGameResult: PlayersGameResult): MutableMap<Player, Double> {
        players.value.forEach { player ->
            playersBettingResult[player] = getPlayerBettingResult(player, dealer, playersGameResult)
        }
        return playersBettingResult
    }

    private fun getPlayerBettingResult(player: Player, dealer: Dealer, playersGameResult: PlayersGameResult): Double {
        if (player.cards.sumOfNumbers() == 21 && player.cards.value.size == 2) {
            return player.userInformation.bettingMoney.value.toDouble() + player.userInformation.bettingMoney.value.toDouble() * 1.5
        }
        if (playersGameResult.value[player] == GameResultType.WIN) return 2 * player.userInformation.bettingMoney.value.toDouble()
        if (player.cards.sumOfNumbers() == 21 && dealer.cards.sumOfNumbers() == 21) return player.userInformation.bettingMoney.value.toDouble()
        if (player.cards.sumOfNumbers() > 21) return 0.0
        return 0.0
    }
}
