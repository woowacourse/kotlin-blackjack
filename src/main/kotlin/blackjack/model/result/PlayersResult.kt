package blackjack.model.result

import blackjack.model.participant.PlayerName
import blackjack.model.participant.Profit

class PlayersResult {
    private val _results = mutableMapOf<PlayerName, Profit>()
    val results: Map<PlayerName, Profit>
        get() = _results.toMap()

    fun add(
        playerName: PlayerName,
        profit: Profit,
    ) {
        _results[playerName] = profit
    }
}
