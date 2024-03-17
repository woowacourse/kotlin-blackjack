package blackjack.model.participant.testState

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult

class Stay : Finish() {
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
        return when {
            myScore > opponentScore -> CompetitionResult.WIN
            myScore < opponentScore -> CompetitionResult.LOSE
            myScore == opponentScore -> CompetitionResult.SAME
            else -> throw IllegalArgumentException("잘못된 값이 들어왔습니다.")
        }
    }
}
