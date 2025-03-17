package blackjack.domain

import blackjack.domain.Profit.Companion.sum
import blackjack.domain.participant.Player

class BlackjackResult(
    private val dealerResult: Map<Player, GameResult>,
    private val playersResult: Map<Player, GameResult>,
) {
    fun dealerProfit(bettingInfo: Map<Player, BettingAmount>): Profit =
        dealerResult.entries
            .map { (player, result) ->
                val bettingAmount = bettingInfo.getOrDefault(player, BettingAmount(0))
                Profit.from(bettingAmount, result)
            }.sum()

    fun playersProfit(bettingInfo: Map<Player, BettingAmount>): Map<Player, Profit> =
        playersResult.entries.associate { (player, result) ->
            val bettingAmount = bettingInfo.getOrDefault(player, BettingAmount(0))
            player to Profit.from(bettingAmount, result)
        }
}
