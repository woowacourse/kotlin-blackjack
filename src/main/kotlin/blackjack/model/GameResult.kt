package blackjack.model

class GameResult(
    val win: Int = DEFAULT_RESULT_VALUE,
    val defeat: Int = DEFAULT_RESULT_VALUE,
    val push: Int = DEFAULT_RESULT_VALUE,
) {
    operator fun plus(newResult: GameResult): GameResult {
        return GameResult(win + newResult.win, defeat + newResult.defeat, push + newResult.push)
    }

    companion object {
        const val DEFAULT_RESULT_VALUE = 0
        private const val INCREASING_VALUE = 1
        private val winResult = GameResult(win = INCREASING_VALUE)
        private val pushResult = GameResult(push = INCREASING_VALUE)
        private val defeatResult = GameResult(defeat = INCREASING_VALUE)

        fun win() = winResult

        fun push() = pushResult

        fun defeat() = defeatResult
    }
}
