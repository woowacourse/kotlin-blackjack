package blackjack.model

import blackjack.model.Participant.Dealer
import blackjack.model.Participant.Player

class GameRevenue(val dealer: Dealer, val players: List<Player>) {
    private val _playersRevenue: MutableList<Double> = mutableListOf()
    val playersRevenue: List<Double>
        get() = _playersRevenue.toList()

    private var _dealerRevenue: Double = DEFAULT_DEALER_AVENUE
    val dealerRevenue: Double
        get() = _dealerRevenue

    init {
        calculateParticipantsRevenue()
    }

    private fun calculateParticipantsRevenue() {
        val playersResult = judgePlayersResult()
        playersResult.withIndex().forEach { (index, playerResult) ->
            val playerRevenue = Calculator.calculatePlayerRevenue(players[index], playerResult)
            _playersRevenue.add(playerRevenue)
            _dealerRevenue -= playerRevenue
        }
    }

    private fun judgePlayersResult(): List<GameResult> {
        val playersGameResult = mutableListOf<GameResult>()
        players.forEach { player ->
            playersGameResult.add(determineWinner(player))
        }
        return playersGameResult
    }

    private fun determineWinner(player: Participant): GameResult {
        if (player.gameInformation.state == GameState.Finished.BUST) return GameResult.DEFEAT
        if (dealer.gameInformation.state == GameState.Finished.BUST) return GameResult.WIN
        val playerScore = Calculator.calculateScore(player.gameInformation.cards)
        val dealerScore = Calculator.calculateScore(dealer.gameInformation.cards)
        return when {
            playerScore > dealerScore -> GameResult.WIN
            playerScore < dealerScore -> GameResult.DEFEAT
            else -> GameResult.TIE
        }
    }

    companion object {
        private const val DEFAULT_DEALER_AVENUE = 0.0
    }
}
