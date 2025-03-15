package blackjack.domain

import blackjack.domain.participant.Player

class BlackjackResult(
    private val dealerResult: Map<Player, GameResult>,
    private val playersResult: Map<Player, GameResult>,
) {
    fun dealerProfit(bettingInfo: Map<Player, BettingAmount>): Profit =
        Profit(
            dealerResult.entries.sumOf { (player, result) ->
                bettingInfo.getOrDefault(player, BettingAmount(0)).profit(result).value
            },
        )

    fun playersProfit(bettingInfo: Map<Player, BettingAmount>): Map<Player, Profit> =
        playersResult.entries.associate { (player, result) ->
            player to bettingInfo.getOrDefault(player, BettingAmount(0)).profit(result)
        }
}
