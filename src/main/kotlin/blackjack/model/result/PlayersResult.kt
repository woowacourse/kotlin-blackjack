package blackjack.model.result

import blackjack.model.participant.PlayerName

class PlayersResult {
    private val _results = mutableMapOf<PlayerName, GameResultType>()
    val results: Map<PlayerName, GameResultType>
        get() = _results.toMap()

    fun add(
        playerName: PlayerName,
        gameResultType: GameResultType,
    ) {
        _results[playerName] = gameResultType
    }
}
