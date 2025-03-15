package blackjack.domain.model.finished

import blackjack.domain.model.Profit
import blackjack.domain.model.betting.BettingPlayers
import blackjack.domain.model.profit.ProfitDealer
import blackjack.domain.model.profit.ProfitParticipants
import blackjack.domain.model.profit.ProfitPlayer

class MatchParticipants(private val matchDealer: MatchDealer, private val matchPlayers: List<MatchPlayer>) {
    fun toProfitPlayers(bettingPlayers: BettingPlayers): ProfitParticipants =
        ProfitParticipants(profitDealer(bettingPlayers), profitPlayers(bettingPlayers))

    private fun profitPlayers(bettingPlayers: BettingPlayers): List<ProfitPlayer> =
        matchPlayers.map { player ->
            val bettingPlayer = bettingPlayers.findPlayer(player.name)
            val profit = bettingPlayer.calculate(player.matchResult)
            ProfitPlayer(player.name, profit)
        }

    private fun profitDealer(bettingPlayers: BettingPlayers): ProfitDealer {
        val dealerProfit =
            matchPlayers.sumOf { player ->
                val bettingPlayer = bettingPlayers.findPlayer(player.name)
                bettingPlayer.calculate(player.matchResult.reverse()).value
            }
        return ProfitDealer(matchDealer.name, Profit(dealerProfit))
    }
}
