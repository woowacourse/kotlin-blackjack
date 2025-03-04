package blackjack.domain

class GameResult(
    private val dealer: Dealer,
) {
    fun calculateWin(players: List<Player>) {
        players.forEach { player ->
            val state = ResultState.calculateWin(player.hand.score(), dealer.hand.score())
            player.updateResultState(state)
        }
    }
}
