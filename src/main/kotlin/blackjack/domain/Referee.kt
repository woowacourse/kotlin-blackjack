package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class Referee {
    private val _gameResult = mutableMapOf<String, Consequence>()
    val gameResult: Map<String, Consequence>
        get() = _gameResult.toMap()

    fun chooseWinner(dealer: Dealer, player: Player) {
        val dealerScore = dealer.cardBunch.getTotalScore()
        val playerScore = player.cardBunch.getTotalScore()
        val gameConsequence = getPlayerConsequence(dealerScore, playerScore)
        _gameResult[player.name] = gameConsequence
    }

    private fun getPlayerConsequence(dealerScore: Int, playerScore: Int): Consequence {
        if (dealerScore == MAX_SCORE_CONDITION) return Consequence.LOSE
        if (playerScore > MAX_SCORE_CONDITION) return Consequence.LOSE
        if (playerScore > dealerScore) return Consequence.WIN
        if (playerScore == dealerScore) return Consequence.DRAW
        return Consequence.LOSE
    }
}
