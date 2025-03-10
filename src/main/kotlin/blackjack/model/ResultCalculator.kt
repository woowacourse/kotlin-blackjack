package blackjack.model

import blackjack.model.ResultType.LOSS
import blackjack.model.ResultType.TIE
import blackjack.model.ResultType.WIN

class ResultCalculator {
    fun judgeScore(
        dealer: Dealer,
        player: Player,
    ): ResultType {
        val dealerFinalScore = if (dealer.isBust()) 0 else dealer.calculateTotalScore()
        val playerFinalScore = if (player.isBust()) 0 else player.calculateTotalScore()
        if (dealerFinalScore < playerFinalScore) return WIN
        if (dealerFinalScore == playerFinalScore) return TIE
        return LOSS
    }

    companion object {
        const val BUST_NUMBER = 21
    }
}
