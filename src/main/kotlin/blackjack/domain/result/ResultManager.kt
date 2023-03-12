package blackjack.domain.result

import blackjack.domain.data.DealerResult
import blackjack.domain.data.ParticipantResults
import blackjack.domain.data.PlayerResult
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import blackjack.domain.participant.Players

class ResultManager(private val dealer: Dealer, private val players: Players) {
    fun judge(): ParticipantResults {
        val gameResults = players.users.map(::judgePlayer)
        val playerProfits = players.users.zip(gameResults).map { (player, result) -> calculatePlayerProfit(player, result) }
        val dealerProfit = playerProfits.fold(0.0) { total, profit -> total - profit }
        val playerResults = players.users.zip(gameResults.zip(playerProfits)).map { (player, result) ->
            PlayerResult(player.name, player.getCards(), player.getTotalScore(), result.first, result.second.toInt())
        }
        val dealerResult = getDealerResult(gameResults, dealerProfit.toInt())
        return ParticipantResults(dealerResult, playerResults)
    }

    private fun judgePlayer(player: Player): GameResult {
        val dealerScore = dealer.getTotalScore()
        val playerScore = player.getTotalScore()

        return when {
            player.isBust() -> GameResult.LOSE
            dealerScore == playerScore -> GameResult.DRAW
            player.isBlackjack() -> GameResult.BLACKJACK
            dealer.isBust() || playerScore > dealerScore -> GameResult.WIN
            else -> GameResult.LOSE
        }
    }

    private fun calculatePlayerProfit(player: Player, result: GameResult): Double = player.money * result.payout

    private fun getDealerResult(results: List<GameResult>, profit: Int): DealerResult {
        with(results) {
            val win = count { it == GameResult.LOSE }
            val draw = count { it == GameResult.DRAW }
            val lose = count { it == GameResult.WIN || it == GameResult.BLACKJACK }
            return DealerResult(dealer.name, dealer.getCards(), dealer.getTotalScore(), win, draw, lose, profit)
        }
    }
}
