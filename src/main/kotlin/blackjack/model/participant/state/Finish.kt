package blackjack.model.participant.state

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult

interface Finish : HandCardState {
    fun getProfit(
        myScore: Int,
        opponentScore: Int,
        battingMoney: BattingMoney,
    ): BattingMoney

    fun getResult(
        myScore: Int,
        opponentScore: Int,
    ): CompetitionResult
}
