package blackjack.domain

class PlayerResults(private val results: Map<BettingPlayer, GameResult>) {

    fun get(): List<PlayerResult> = results.map { (player, result) -> PlayerResult(player.getName(), result) }

    fun getDealerResult(): Map<GameResult, Int> {
        with(results.values) {
            val win = count { it == GameResult.LOSE }
            val draw = count { it == GameResult.DRAW }
            val lose = count { it == GameResult.WIN || it == GameResult.BLACKJACK }
            return mapOf((GameResult.WIN to win), (GameResult.DRAW to draw), (GameResult.LOSE to lose))
        }
    }

    fun calculateProfits(): List<ParticipantProfit> {
        var dealerProfit = 0.0
        val playerProfits = mutableListOf<ParticipantProfit>()

        results.forEach { (player, result) ->
            val profit = player.money * result.payout
            dealerProfit -= profit
            playerProfits.add(ParticipantProfit(player.getName(), profit.toInt()))
        }

        playerProfits.add(0, ParticipantProfit("딜러", dealerProfit.toInt()))
        return playerProfits
    }
}
