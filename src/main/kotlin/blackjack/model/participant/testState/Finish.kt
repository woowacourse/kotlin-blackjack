package blackjack.model.participant.testState

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult

abstract class Finish : HandCardState {
    abstract fun getProfit(
        opponentScore: Int,
        battingMoney: BattingMoney,
    ): BattingMoney

    abstract fun getResult(opponentScore: Int): CompetitionResult
}
