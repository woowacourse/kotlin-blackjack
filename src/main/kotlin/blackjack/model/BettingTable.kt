package blackjack.model

import blackjack.model.participant.Participant

class BettingTable(
    val participant: Participant,
    private val bettingAmount: Money,
) {
    fun getProfit(): Money {
        val participantState = participant.state
        return participantState.expectedProfit(bettingAmount)
    }

    fun getProfit(profitRate: Float): Money = bettingAmount * profitRate
}
