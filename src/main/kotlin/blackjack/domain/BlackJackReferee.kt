package blackjack.domain

import blackjack.domain.dealer.Dealer
import blackjack.domain.gameResult.*
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

    private fun judgePlayerGameResults(players: List<Player>, dealer: Dealer): PlayerGameResults =
        PlayerGameResults(
            players.map { player ->
                PlayerGameResult(
                    playerName = player.name.value,
                    profitMoney = ProfitMoney(
                        player.battingMoney,
                        GameResult.valueOf(player.cards, dealer.cards)
                    )
                )
            }
        )

    private fun judgeDealerGameResults(playerProfitResults: PlayerGameResults): ProfitMoney {
        val totalProfitMoney = playerProfitResults.getPlayersTotalProfit()

        return !totalProfitMoney
    }
}
