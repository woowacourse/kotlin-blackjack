package blackjack.model.participant.testState

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult

class Blackjack2() : Finish() {
    fun getProfit(
        opponentScore: Int,
        battingMoney: BattingMoney,
    ): BattingMoney {
        return battingMoney.times(getResult(opponentScore).profit)
    }

    private fun getResult(opponentScore: Int): CompetitionResult {
        return if (opponentScore == 21) {
            CompetitionResult.SAME
        } else {
            CompetitionResult.BLACKJACK
        }
    }
}
