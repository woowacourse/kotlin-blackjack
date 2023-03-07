package blackjack.domain

import blackjack.domain.dealer.Dealer
import blackjack.domain.gameResult.GameResult
import blackjack.domain.gameResult.PlayerGameResult
import blackjack.domain.gameResult.ProfitMoney
import blackjack.domain.gameResult.TotalGameResult
import blackjack.domain.player.Player

class BlackJackReferee {

    private fun judgePlayerGameResults(players: List<Player>, dealer: Dealer) = players.map { player ->
        PlayerGameResult(
            playerName = player.name.value,
            profitMoney = ProfitMoney(
                player.battingMoney,
                GameResult.valueOf(player.cards, dealer.cards)
            )
        )
    }

    private fun judgeDealerGameResults(playerProfitResults: List<PlayerGameResult>): ProfitMoney {
        val totalProfitValue = playerProfitResults.sumOf { playerProfitResult ->
            playerProfitResult.profitMoney.value
        }

        return !ProfitMoney(totalProfitValue)
    }

    fun judgeTotalGameResults(players: List<Player>, dealer: Dealer): TotalGameResult {
        val playerGameResults = judgePlayerGameResults(players, dealer)
        val dealerGameResult = judgeDealerGameResults(playerGameResults)

        return TotalGameResult(
            playerGameResults = playerGameResults,
            dealerGameResults = dealerGameResult
        )
    }
}
