package blackjack.model

import blackjack.model.Participant.Player

sealed class Revenue {
    abstract val amount: Double

    abstract fun calculateRevenue(): Double

    override fun toString(): String {
        return amount.toInt().toString()
    }

    class PlayerRevenue(private val player: Player, private val result: Result) : Revenue() {
        override val amount: Double = calculateRevenue()

        override fun calculateRevenue(): Double {
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
            return revenueAmount
        }

        companion object {
            private const val BLACKJACK_REVENUE_MULTIPLY = 1.5
            private const val WIN_REVENUE_MULTIPLY = 1.0
            private const val TIE_REVENUE_MULTIPLY = 0.0
            private const val DEFEAT_REVENUE_MULTIPLY = -1.0
        }
    }

    class DealerRevenue(private val playersRevenue: List<PlayerRevenue>) : Revenue() {
        override val amount: Double = calculateRevenue()

        override fun calculateRevenue(): Double {
            return playersRevenue.sumOf { playerRevenue ->
                -playerRevenue.amount
            }
        }
    }
}
