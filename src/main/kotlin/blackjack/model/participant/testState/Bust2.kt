package blackjack.model.participant.testState

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult

class Bust2 : Finish() {
    override fun getProfit(
        myScore: Int,
        opponentScore: Int,
        battingMoney: BattingMoney,
    ): BattingMoney {
        return battingMoney.times(getResult(opponentScore, myScore).profit)
    }

    override fun getResult(
        myScore: Int,
        opponentScore: Int,
    ): CompetitionResult {
        return if (myScore == opponentScore) {
            CompetitionResult.SAME
        } else {
            CompetitionResult.LOSE
        }
    }
}
