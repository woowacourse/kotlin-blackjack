package blackjack.model

import blackjack.model.ResultType.LOSS
import blackjack.model.ResultType.TIE
import blackjack.model.ResultType.WIN

object ResultCalculator {
    const val BUST_NUMBER = 21
    private const val ADJUST_ACE_NUMBER = 10

    fun adjustScore(cards: List<Card>): Int {
        var sumScore = calculateTotalScore(cards)
        var countAce = countAce(cards)
        while (countAce-- > 0) {
            if (sumScore > BUST_NUMBER) {
                sumScore -= ADJUST_ACE_NUMBER
            }
        }
        return sumScore
    }

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
