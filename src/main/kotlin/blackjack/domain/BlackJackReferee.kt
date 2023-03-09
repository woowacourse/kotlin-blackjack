package blackjack.domain

import blackjack.domain.dealer.Dealer
import blackjack.domain.gameResult.PlayerGameResult
import blackjack.domain.gameResult.PlayerGameResults
import blackjack.domain.gameResult.ProfitMoney
import blackjack.domain.gameResult.TotalGameResult
import blackjack.domain.player.Player

class BlackJackReferee {

    fun judgeTotalGameResults(players: List<Player>, dealer: Dealer): TotalGameResult {
        val playerGameResults = judgePlayerGameResults(players, dealer)
        val dealerGameResult = judgeDealerGameResults(playerGameResults)

        return TotalGameResult(
            playerGameResults = playerGameResults,
            dealerGameResults = dealerGameResult
        )
    }

    private fun judgePlayerGameResults(players: List<Player>, dealer: Dealer): PlayerGameResults {
        val playerGameResults = players.map { player ->
            PlayerGameResult.of(player, dealer)
        }

        return PlayerGameResults(playerGameResults)
    }

    private fun judgeDealerGameResults(playerProfitResults: PlayerGameResults): ProfitMoney {
        val totalProfitMoney = playerProfitResults.getPlayersTotalProfit()

        return !totalProfitMoney
    }
}
