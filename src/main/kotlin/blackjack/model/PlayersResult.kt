package blackjack.model

class PlayersResult {
    private val _results = mutableMapOf<PlayerName, GameResult>()
    val results: Map<PlayerName, GameResult>
        get() = _results.toMap()

    fun add(
        playerName: PlayerName,
        gameResult: GameResult,
    ) {
        _results[playerName] = gameResult
    }
}
