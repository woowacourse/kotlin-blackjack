package blackjack.domain

enum class GameResult(
    private val playerMultiplier: Double,
    private val dealerMultiplier: Double,
) {
    WIN(1.0, 1.0),
    LOSE(-1.0, -1.0),
    DRAW(0.0, 0.0),
    BLACKJACK(1.5, 1.0),
    ;

    fun calculatePlayerProfit(bettingMoney: Money): Double = bettingMoney.value * playerMultiplier

    fun calculateDealerProfit(bettingMoney: Money): Double = bettingMoney.value * dealerMultiplier
}
