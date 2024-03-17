package blackjack.model.playing.participants

import blackjack.model.card.CardDeck
import blackjack.model.playing.participants.player.Player
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.playing.participants.player.Players
import blackjack.model.winning.DealerWinning
import blackjack.model.winning.FinalWinning
import blackjack.model.winning.PlayersWinning
import blackjack.model.winning.WinningResultStatus

data class Participants(val dealer: Dealer, val players: Players) {
    fun addInitialCards(cardDeck: CardDeck) {
        dealer.addInitialCards(cardDeck)
        players.players.forEach {
            it.addInitialCards(cardDeck)
        }
    }

    fun getFinalWinning(): FinalWinning {
        val dealerResult = judgeDealerWinning()
        val dealerWinning = DealerWinning(getVictoryCount(dealerResult), getDefeatCount(dealerResult), getPushCount(dealerResult))
        val playersWinning = judgePlayersWinning()

        return FinalWinning(dealerWinning, playersWinning)
    }

    fun getProfit(playersWinning: PlayersWinning): Map<PlayerName, Double> {
        val profit = mutableMapOf<PlayerName, Double>()
        var dealerProfit = 0.0

        players.players.forEach { player ->
            val playerProfit = calculatePlayerProfit(playersWinning, player, profit)
            dealerProfit -= playerProfit
        }
        profit[dealer.name] = dealerProfit
        return profit
    }

    private fun calculatePlayerProfit(
        playersWinning: PlayersWinning,
        player: Player,
        profit: MutableMap<PlayerName, Double>,
    ): Double {
        var playerProfit = 0.0
        playersWinning.result.forEach { (playerName, winningResult) ->
            if (player.name == playerName) {
                playerProfit = player.calculateProfit(winningResult)
            }
        }
        profit[player.name] = playerProfit
        return playerProfit
    }

    private fun getVictoryCount(dealerResult: Map<WinningResultStatus, Int>): Int =
        dealerResult.getOrDefault(
            WinningResultStatus.VICTORY,
            0,
        )

    private fun getDefeatCount(dealerResult: Map<WinningResultStatus, Int>): Int = dealerResult.getOrDefault(WinningResultStatus.DEFEAT, 0)

    private fun getPushCount(dealerResult: Map<WinningResultStatus, Int>): Int = dealerResult.getOrDefault(WinningResultStatus.PUSH, 0)

    private fun judgeDealerWinning(): Map<WinningResultStatus, Int> {
        val dealerWinning = mutableMapOf<WinningResultStatus, Int>().withDefault { 0 }

        players.players.forEach {
            val winningStatus = dealer.match(it)
            dealerWinning[winningStatus] = dealerWinning.getValue(winningStatus) + 1
        }
        return dealerWinning
    }

    private fun judgePlayersWinning(): PlayersWinning = PlayersWinning(players.players.associate { it.name to it.match(dealer) })
}
