package blackjack.model.participant

import blackjack.model.result.GameResultType

class Player(val name: PlayerName, val battingAmount: BattingAmount) : Role() {
    override fun decideMoreCard() = !isBlackjack() && !isMaxScore() && !isBurst()

    fun calculateProfit(result: GameResultType) {
        if (result == GameResultType.WIN) {
            if (isBlackjack()) {
                profit += (BLACKJACK_BONUS * battingAmount.amount).toInt()
                return
            }
            profit += battingAmount.amount
            return
        }
        if (result == GameResultType.LOSE) profit -= battingAmount.amount
    }

    companion object {
        private const val BLACKJACK_BONUS = 1.5
    }
}
