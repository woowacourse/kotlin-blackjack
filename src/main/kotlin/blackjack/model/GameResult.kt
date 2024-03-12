package blackjack.model

data class GameResult(
    val win: Int = DEFAULT_RESULT_VALUE,
    val defeat: Int = DEFAULT_RESULT_VALUE,
    val push: Int = DEFAULT_RESULT_VALUE,
) {
    fun setResultTo(
        newWin: Int = DEFAULT_RESULT_VALUE,
        newDefeat: Int = DEFAULT_RESULT_VALUE,
        newPush: Int = DEFAULT_RESULT_VALUE,
    ): GameResult {
        return copy(win = win + newWin, defeat = defeat + newDefeat, push = push + newPush)
    }

    companion object {
        const val DEFAULT_RESULT_VALUE = 0
    }
}
