package model

class GameResult private constructor(val playersResult: Map<String, Boolean>) {

    fun getDealerWinResult(): Int = playersResult.count { !it.value }
    fun getDealerLoseResult(): Int = playersResult.count { it.value }

    companion object {
        private const val BLACKJACK_POINT = 21
        private fun match(dealer: Dealer, player: Player): Boolean {
            if (player.isBust()) return false
            if (dealer.isBust()) return true
            return (BLACKJACK_POINT - dealer.cards.sum()) > (BLACKJACK_POINT - player.cards.sum())
        }

        fun of(dealer: Dealer, players: List<Player>): GameResult =
            GameResult(buildMap { players.forEach { put(it.name.value, match(dealer, it)) } })
    }
}
