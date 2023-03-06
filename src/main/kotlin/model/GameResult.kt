package model

import model.Cards.Companion.PARTICIPANT_STANDARD_BUST_POINT

class GameResult private constructor(val playersResult: Map<String, Boolean>) {

    fun getDealerWinResult(): Int = playersResult.count { !it.value }
    fun getDealerLoseResult(): Int = playersResult.count { it.value }

    companion object {
        private fun match(dealer: Dealer, player: Player): Boolean {
            if (player.isBust()) return false
            if (dealer.isBust()) return true
            return (PARTICIPANT_STANDARD_BUST_POINT - dealer.cards.sum()) > (PARTICIPANT_STANDARD_BUST_POINT - player.cards.sum())
        }

        fun of(dealer: Dealer, players: List<Player>): GameResult =
            GameResult(buildMap { players.forEach { put(it.name.value, match(dealer, it)) } })
    }
}
