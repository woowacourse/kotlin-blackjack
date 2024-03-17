package blackjack.model.participant.state

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult

abstract class Finish : HandCardState {
    abstract fun getProfit(
        myScore: Int,
        opponentScore: Int,
        battingMoney: BattingMoney,
    ): BattingMoney

    abstract fun getResult(
        myScore: Int,
        opponentScore: Int,
    ): CompetitionResult
}
