package blackjack.uimodel

import blackjack.domain.betting.ProfitAmount
import blackjack.domain.participants.Player

data class ResultUiModel(
    val name: String,
    val profit: Int,
) {
    companion object {
        fun create(profitResult: Map<Player, ProfitAmount>): List<ResultUiModel> {
            val dealerProfit = -profitResult.values.sumOf { it.value }
            val dealerResult =
                ResultUiModel(
                    name = "딜러",
                    profit = dealerProfit,
                )

            val playersResult =
                profitResult.map { (player, profitAmount) ->
                    ResultUiModel(
                        name = player.name,
                        profit = profitAmount.value,
                    )
                }

            return listOf(dealerResult) + playersResult
        }
    }
}
