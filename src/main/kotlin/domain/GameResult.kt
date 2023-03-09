package domain

class GameResult(private val participants: Participants) {
    private fun getPlayersGameResult(): Map<Player, GameResultType> {
        return participants.players.list.associateWith { player -> player.getGameResult(participants.dealer.getScore()) }
    }

    fun getPlayersProfit(): Map<Player, Int> {
        val gameResult = getPlayersGameResult()
        return gameResult.keys.associateWith { player ->
            GameResultType.getProfit(player.bettingMoney.money, gameResult[player] ?: throw IllegalArgumentException())
        }
    }

    fun getDealerProfit(): Pair<Dealer, Int> {
        val playersProfit = getPlayersProfit()
        return Pair(participants.dealer, -playersProfit.values.sum())
    }
}
