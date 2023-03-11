package blackjack.domain.result

import blackjack.domain.data.DealerResult
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.PlayerResult
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

class GameResults(private val dealer: Dealer, private val results: Map<Player, GameResult>) {
    fun getResults(): ParticipantResults {
        val playerResults = mutableListOf<PlayerResult>()
        var dealerProfit = 0.0
        results.forEach { (player, result) ->
            val profit = player.info.money * result.payout
            dealerProfit -= profit
            playerResults.add(getPlayerResult(player, result, profit.toInt()))
        }
        val dealerResult = getDealerResult(dealerProfit.toInt())
        return ParticipantResults(dealerResult, playerResults)
    }

    private fun getPlayerResult(player: Player, result: GameResult, profit: Int): PlayerResult {
        return PlayerResult(player.info.name, player.getCards(), player.getTotalScore(), result, profit)
    }

    private fun getDealerResult(profit: Int): DealerResult {
        with(results.values) {
            val win = count { it == GameResult.LOSE }
            val draw = count { it == GameResult.DRAW }
            val lose = count { it == GameResult.WIN || it == GameResult.BLACKJACK }
            return DealerResult(dealer.name, dealer.getCards(), dealer.getTotalScore(), win, draw, lose, profit)
        }
    }
}
