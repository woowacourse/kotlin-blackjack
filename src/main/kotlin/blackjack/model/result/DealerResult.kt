package blackjack.model.result

class DealerResult {
    private val _results = mutableMapOf<GameResultType, Int>()
    val results: Map<GameResultType, Int>
        get() = _results.toMap()

    fun add(gameResultType: GameResultType) {
        _results[gameResultType] = _results.getOrDefault(gameResultType, GAME_RESULT_DEFAULT_COUNT) + 1
    }

    companion object {
        private const val GAME_RESULT_DEFAULT_COUNT = 0
    }
}
