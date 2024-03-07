package blackjack.model

class DealerResult {
    private val _results = mutableMapOf<GameResult, Int>()
    val results: Map<GameResult, Int>
        get() = _results.toMap()

    fun add(gameResult: GameResult) {
        _results[gameResult] = _results.getOrDefault(gameResult, GAME_RESULT_DEFAULT_COUNT) + 1
    }

    companion object {
        private const val GAME_RESULT_DEFAULT_COUNT = 0
    }
}
