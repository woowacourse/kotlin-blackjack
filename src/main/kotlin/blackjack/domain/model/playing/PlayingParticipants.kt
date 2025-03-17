package blackjack.domain.model.playing

import blackjack.domain.model.Deck
import blackjack.domain.model.Profit
import blackjack.domain.model.betting.BettingPlayers
import blackjack.domain.model.profit.ProfitParticipant
import blackjack.domain.model.profit.ProfitParticipants

class PlayingParticipants(val dealer: PlayingDealer, val players: List<PlayingPlayer>) {
    val participants get() = listOf(dealer, *players.toTypedArray())

    fun dealInitialCard(deck: Deck) {
        participants.forEach { playingParticipant ->
            playingParticipant.acceptCard(deck.draw())
        }
    }

    fun toProfitParticipants(bettingPlayers: BettingPlayers) =
        ProfitParticipants(profitDealer(bettingPlayers), profitPlayers(bettingPlayers))

    private fun profitPlayers(bettingPlayers: BettingPlayers): List<ProfitParticipant> =
        players.map { player ->
            val bettingPlayer = bettingPlayers.findPlayer(player.name)
            val profit = bettingPlayer.calculate(player.match(dealer.handsState))
            ProfitParticipant(player.name, profit)
        }

    private fun profitDealer(bettingPlayers: BettingPlayers): ProfitParticipant {
        val dealerProfit =
            players.sumOf { player ->
                val bettingPlayer = bettingPlayers.findPlayer(player.name)
                bettingPlayer.calculate(player.match(dealer.handsState).reverse()).value
            }
        return ProfitParticipant(dealer.name, Profit(dealerProfit))
    }
}
