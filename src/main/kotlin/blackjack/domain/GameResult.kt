package blackjack.domain

import blackjack.domain.betting.BettingInfo
import blackjack.domain.betting.ProfitAmount
import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Participant

class GameResult private constructor(val results: Map<Participant, ProfitAmount>) {
    companion object {
        fun create(
            dealer: Dealer,
            bettingInfos: List<BettingInfo>,
        ): GameResult {
            val playerProfits =
                bettingInfos.associate { bettingInfo ->
                    bettingInfo.player to dealer.calculatePlayersProfit(bettingInfo)
                }
            val dealerProfit = ProfitAmount(-playerProfits.values.sumOf { it.value })

            return GameResult(mapOf(dealer to dealerProfit) + playerProfits)
        }
    }
}
