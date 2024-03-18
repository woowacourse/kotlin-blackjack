package blackjack.model

class Participants(
    val dealer: Dealer,
    val playerGroup: PlayerGroup,
) {
    fun betBeforeSetting(getBettingMoney: (playerName: String) -> String) {
        playerGroup.placeBettingMoney(getBettingMoney = getBettingMoney)
    }

    fun initSetting(gameDeck: GameDeck) {
        dealer.initHands(gameDeck)
        playerGroup.players.forEach { player ->
            player.initHands(gameDeck)
        }
    }

    fun matchResult() {
        playerGroup.players.forEach { player ->
            player.addResult(player.state.decideWinner(dealer))
        }
        calculateDealerProfit()
    }

    private fun calculateDealerProfit() {
        playerGroup.players.forEach { player ->
            dealer.state.profit.getProfitFromPlayer(player.state.profit.amount)
        }
    }
}
