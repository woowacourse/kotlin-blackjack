class GameResult private constructor(val playersResult: Map<String, Boolean>) {

    fun getDealerWinResult(): Int = playersResult.count { !it.value }
    fun getDealerLoseResult(): Int = playersResult.count { it.value }

    companion object {
        private const val BLACKJACK_POINT = 21
        private fun match(dealer: Dealer, player: Player): Boolean {
            return if (player.isBurst()) {
                false
            } else if (dealer.isBurst()) {
                true
            } else {
                (BLACKJACK_POINT - dealer.cards.sum()) > (BLACKJACK_POINT - player.cards.sum())
            }
        }

        fun of(dealer: Dealer, players: List<Player>): GameResult {
            val playersResult = mutableMapOf<String, Boolean>()
            for (player in players) {
                playersResult[player.name.value] = match(dealer, player)
            }
            return GameResult(playersResult)
        }
    }
}
