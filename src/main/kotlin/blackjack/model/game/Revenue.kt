package blackjack.model.game

import blackjack.model.user.Participant.Player

class Revenue(amount: Double = DEFAULT_REVENUE_AMOUNT) {
    private var _amount: Double = amount
    val amount: Double
        get() = _amount

    fun calculateRevenue(
        player: Player,
        result: Result,
    ) {
        val bettingAmount = player.playerInformation.bettingAmount.amount
        val revenueAmount =
            when (result) {
                Result.WIN -> {
                    if (player.gameInformation.state == GameState.Finished.BLACKJACK) {
                        bettingAmount * BLACKJACK_REVENUE_MULTIPLY
                    } else {
                        bettingAmount * WIN_REVENUE_MULTIPLY
                    }
                }

                Result.DEFEAT -> bettingAmount * DEFEAT_REVENUE_MULTIPLY
                Result.TIE -> bettingAmount * TIE_REVENUE_MULTIPLY
            }
        _amount = revenueAmount
    }

    override fun toString(): String {
        return amount.toInt().toString()
    }

    companion object {
        private const val DEFAULT_REVENUE_AMOUNT = 0.0
        private const val BLACKJACK_REVENUE_MULTIPLY = 1.5
        private const val WIN_REVENUE_MULTIPLY = 1.0
        private const val TIE_REVENUE_MULTIPLY = 0.0
        private const val DEFEAT_REVENUE_MULTIPLY = -1.0
    }
}
