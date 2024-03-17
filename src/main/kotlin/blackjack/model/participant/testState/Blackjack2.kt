package blackjack.model.participant.testState

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult

class Blackjack2() : Finish() {
    override fun getProfit(
        myScore: Int,
        opponentScore: Int,
        battingMoney: BattingMoney,
    ): BattingMoney {
        return battingMoney.times(getResult(myScore, opponentScore).profit)
    }

    override fun getResult(
        myScore: Int,
        opponentScore: Int,
    ): CompetitionResult {
        return if (opponentScore == myScore) {
            CompetitionResult.SAME
        } else {
            CompetitionResult.BLACKJACK
        }
    }
}
