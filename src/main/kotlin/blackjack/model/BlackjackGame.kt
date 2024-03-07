package blackjack.model

object BlackjackGame {
    fun updateGameResult(
        players: Players,
        dealer: Dealer,
    ) {
        players.playerGroup.forEach { player ->
            val gameResult = dealer.decideGameResult(player)
            dealer.result.add(gameResult)
            players.playersResult.add(player.name, gameResult.reverse())
        }
    }
}
