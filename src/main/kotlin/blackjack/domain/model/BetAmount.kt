package blackjack.domain.model

class BetAmount(val money: Int) {
    fun calculateProceed(
        result: GameResult,
        isBlackjack: Boolean,
    ): Int =
        when {
            result == GameResult.Lose -> -money
            result == GameResult.Draw && isBlackjack -> money
            result == GameResult.Draw -> 0
            result == GameResult.Win && isBlackjack -> (1.5 * money).toInt()
            result == GameResult.Win -> money
            else -> 0
        }
}
