package blackjack.model

import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player

class GameResult(val dealer: Dealer, val players: List<Player>) {
    private val _playerResults: MutableList<Result> = mutableListOf()
    val playerResults: List<Result>
        get() = _playerResults.toList()

    private val _dealerResult: MutableMap<Result, Int> = mutableMapOf()
    val dealerResult: Map<Result, Int>
        get() = _dealerResult.toMap()

    init {
        generatePlayerResults()
        generateDealerResult()
    }

    private fun generatePlayerResults() {
        players.forEach { player ->
            _playerResults.add(determineWinner(player))
        }
    }

    private fun determineWinner(player: Participant): Result {
        if (player.gameInformation.state == GameState.Finished.BUST) return Result.DEALER_WIN
        if (dealer.gameInformation.state == GameState.Finished.BUST) return Result.PLAYER_WIN
        val playerScore = player.gameInformation.score
        val dealerScore = dealer.gameInformation.score
        return when {
            playerScore > dealerScore -> Result.PLAYER_WIN
            playerScore < dealerScore -> Result.DEALER_WIN
            else -> Result.TIE
        }
    }

    private fun generateDealerResult() {
        val winCount = playerResults.count { result -> result == Result.DEALER_WIN }
        val defeatCount = playerResults.count { result -> result == Result.PLAYER_WIN }
        val tieCount = playerResults.count { result -> result == Result.TIE }
        _dealerResult[Result.DEALER_WIN] = winCount
        _dealerResult[Result.PLAYER_WIN] = defeatCount
        _dealerResult[Result.TIE] = tieCount
    }
}
