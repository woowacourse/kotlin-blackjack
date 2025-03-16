package blackjack.domain

enum class GameResult(
    private val profitRate: Double,
) {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0),
    ;

    fun calculateProfit(bettingAmount: Double): Double = (bettingAmount * profitRate)
}
