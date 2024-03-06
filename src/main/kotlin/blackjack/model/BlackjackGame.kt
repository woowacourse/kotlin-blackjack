package blackjack.model

object BlackjackGame {
    fun updateGameResult(
        players: Players,
        dealer: Dealer,
    ) {
        dealer.optimizeCardSum()
        players.playerGroup.forEach { player ->
            player.optimizeCardSum()
            val gameResult = player.decideGameResult(dealer)
            players.playersResult.add(player.name, gameResult)
            dealer.result.add(gameResult.reverse())
        }
    }
}
