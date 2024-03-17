package blackjack.model.participant.testState

import blackjack.model.BattingMoney
import blackjack.model.deck.HandCards
import blackjack.model.participant.CompetitionResult

class Stay(private val handCards: HandCards) : Finish() {
    override fun getProfit(
        opponentScore: Int,
        battingMoney: BattingMoney,
    ): BattingMoney {
        return battingMoney.times(getResult(opponentScore).profit)
    }

    override fun getResult(opponentScore: Int): CompetitionResult {
        return when {
            handCards.calculateCardScore() > opponentScore -> CompetitionResult.WIN
            handCards.calculateCardScore() < opponentScore -> CompetitionResult.LOSE
            handCards.calculateCardScore() == opponentScore -> CompetitionResult.SAME
            else -> throw IllegalArgumentException("잘못된 값이 들어왔습니다.")
        }
    }
}
