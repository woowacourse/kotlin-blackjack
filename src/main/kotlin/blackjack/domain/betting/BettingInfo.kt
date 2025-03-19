package blackjack.domain.betting

import blackjack.domain.participants.Player
import blackjack.domain.state.ResultState

data class BettingInfo(
    val player: Player,
    val bettingAmount: BettingAmount,
) {
    fun calculateProfitAmount(resultState: ResultState): ProfitAmount {
        return ProfitAmount(bettingAmount.value * resultState.profitRate)
    }
}
