package blackjack.model.participant

import blackjack.model.result.GameResultType

class Player(val name: PlayerName) : Role() {
    lateinit var battingAmount: BattingAmount

    override fun decideMoreCard() = !isBlackjack() && !isMaxScore() && !isBurst()

    fun calculateProfit(result: GameResultType) {
        if (result == GameResultType.WIN) {
            if (isBlackjack()) profit = (1.5 * battingAmount.amount).toInt()
        }
        if (result == GameResultType.LOSE) profit -= battingAmount.amount
    }
}
