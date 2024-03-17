package blackjack.model.participant.state

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
            opponentScore > 21 -> CompetitionResult.WIN
            myScore > opponentScore -> CompetitionResult.WIN
            myScore < opponentScore -> CompetitionResult.LOSE
            myScore == opponentScore -> CompetitionResult.SAME
            else -> throw IllegalArgumentException("잘못된 값이 들어왔습니다.")
        }
    }

    override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState {
        throw IllegalStateException("더 이상 턴을 진행할 수 없습니다.")
    }
}
