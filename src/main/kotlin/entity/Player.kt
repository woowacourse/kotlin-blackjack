package entity

import misc.GameRule

class Player(val name: String, cards: Cards = Cards(listOf())) : User(cards) {
    override fun isDistributable(): Boolean = cardsNumberSum() < GameRule.WINNING_NUMBER

    fun addMoreCards(condition: String, onDistribute: () -> Unit) {
        if (condition == "y") {
            onDistribute()
        }
    }

    fun determineGameResult(dealerCardNumberSum: Int): Pair<Player, GameResultType> {
        val playerCardNumberSum = cardsNumberSum()
        return if (playerCardNumberSum in (dealerCardNumberSum + 1)..GameRule.WINNING_NUMBER || (GameRule.WINNING_NUMBER in playerCardNumberSum until dealerCardNumberSum)) Pair(
            this,
            GameResultType.WIN
        )
        else if (playerCardNumberSum == dealerCardNumberSum || (playerCardNumberSum > GameRule.WINNING_NUMBER && dealerCardNumberSum > GameRule.WINNING_NUMBER)) Pair(
            this,
            GameResultType.DRAW
        )
        else Pair(this, GameResultType.LOSE)
    }
}
