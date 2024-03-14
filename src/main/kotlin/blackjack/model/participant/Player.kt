package blackjack.model.participant

import blackjack.model.result.GameResultType

class Player(val name: PlayerName, val battingAmount: Int = 0) : Role() {
    override fun decideMoreCard() = !isBlackjack() && !isMaxScore() && !isBurst()

    fun calculateProfit(result: GameResultType) {
        if (result == GameResultType.WIN) {
            if (isBlackjack()) profit = (1.5 * battingAmount).toInt()
        }
        if (result == GameResultType.DRAW) profit = battingAmount
        profit -= battingAmount
    }
}
