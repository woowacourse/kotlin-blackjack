package domain

class GameResult(private val participants: Participants) {
    fun getPlayersProfit(): Map<Player, Int> {
        return participants.players.list.associateWith { player -> player.getProfit(participants.dealer.getScore()) }
    }

    fun getDealerProfit(): Pair<Dealer, Int> {
        val playersProfit = getPlayersProfit()
        return Pair(participants.dealer, -playersProfit.values.sum())
    }
}
