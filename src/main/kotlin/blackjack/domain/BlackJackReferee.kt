package blackjack.domain

import blackjack.domain.dealer.Dealer
import blackjack.domain.gameResult.GameResult
import blackjack.domain.gameResult.PlayerGameResult
import blackjack.domain.gameResult.TotalGameResult
import blackjack.domain.player.Player

class BlackJackReferee {

    private fun judgePlayerGameResults(players: List<Player>, dealer: Dealer) = players.map { player ->
        PlayerGameResult(
            playerName = player.name.value,
            gameResult = GameResult.valueOf(player.cards, dealer.cards)
        )
    }

    private fun judgeDealerGameResults(playerGameResults: List<PlayerGameResult>) = playerGameResults.map { playerGameResult ->
        !playerGameResult.gameResult
    }

    fun judgeTotalGameResults(players: List<Player>, dealer: Dealer): TotalGameResult {
        val playersGameResult = judgePlayerGameResults(players, dealer)
        val dealerGameResult = judgeDealerGameResults(playersGameResult)

        return TotalGameResult(
            playersGameResult = playersGameResult,
            dealerGameResults = dealerGameResult
        )
    }
}
