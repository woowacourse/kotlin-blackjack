package blackjack.model

class GameResult(
    val win: Int = DEFAULT_RESULT_VALUE,
    val defeat: Int = DEFAULT_RESULT_VALUE,
    val push: Int = DEFAULT_RESULT_VALUE,
) {
    operator fun plus(newResult: GameResult): GameResult {
        return GameResult(win + newResult.win,defeat + newResult.defeat,push + newResult.push)
    }

    companion object {
        const val DEFAULT_RESULT_VALUE = 0
    }
}
