package blackjack.model

data class GameResult(
    val win: Int = DEFAULT_RESULT_VALUE,
    val lose: Int = DEFAULT_RESULT_VALUE,
) {
    fun calculate(betAmount: BetAmount): Int {
        return (betAmount.amount * win) - (betAmount.amount * lose)
    }

    companion object {
        const val DEFAULT_RESULT_VALUE = 0
    }
}
