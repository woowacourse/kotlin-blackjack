package blackjack.model.participant.state

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult
import java.lang.IllegalStateException

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
        return CompetitionResult.LOSE
    }

    override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState {
        throw IllegalStateException("더 이상 턴을 진행할 수 없습니다.")
    }
}
