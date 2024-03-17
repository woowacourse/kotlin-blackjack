package blackjack.model.participant.testState

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult
import java.lang.IllegalStateException

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

    override fun nextTurn(
        myScore: Int,
        isHit: Boolean,
    ): HandCardState {
        throw IllegalStateException("더 이상 턴을 진행할 수 없습니다.")
    }
}
