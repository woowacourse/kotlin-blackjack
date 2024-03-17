package blackjack.model.participant.testState

import blackjack.model.BattingMoney
import blackjack.model.participant.CompetitionResult

class Blackjack2() : Finish() {
    override fun getProfit(
        opponentScore: Int,
        battingMoney: BattingMoney,
    ): BattingMoney {
        return battingMoney.times(getResult(opponentScore).profit)
    }

    override fun getResult(opponentScore: Int): CompetitionResult {
        return if (opponentScore == 21) {
            CompetitionResult.SAME
        } else {
            CompetitionResult.BLACKJACK
        }
    }
}
