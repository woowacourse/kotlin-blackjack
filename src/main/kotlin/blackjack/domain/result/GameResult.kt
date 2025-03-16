package blackjack.domain.result

import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.domain.person.PlayerBetInfo

class GameResult(dealer: Dealer, playerBetInfos: List<PlayerBetInfo>) {
    val playerPayouts: Map<Player, Profit> =
        playerBetInfos.associateBy(
            { info -> info.player },
            { info -> info.calculatePlayerPayout(dealer) },
        )
    val dealerProfit: Profit = calculateDealerProfit()

    private fun calculateDealerProfit(): Profit {
        val profit = playerPayouts.values.sumOf { it.value } * NEGATIVE_ONE
        val result = if (profit == NEGATIVE_ZERO_PROFIT) ZERO_PROFIT else profit
        return Profit(result)
    }

    companion object {
        const val ZERO_PROFIT = 0.0
        const val NEGATIVE_ZERO_PROFIT = -0.0
        const val NEGATIVE_ONE = -1
    }
}
