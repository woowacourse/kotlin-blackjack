package entity

import model.BlackjackStage
import model.CardFactory

class Player(val name: Name, private val bet: Money, val cards: Cards = Cards()) : User {
    override fun isDistributable(): Boolean = cards.sumOfNumbers() < BlackjackStage.WINNING_NUMBER

    fun addMoreCards(cardFactory: CardFactory) {
        cards.addCards(cardFactory.generate(User.SINGLE_DISTRIBUTE_COUNT))
    }

    fun determineGameResult(dealerCardNumberSum: Int): GameResultType {
        val playerCardNumberSum = cards.sumOfNumbers()
        return when {
            isWin(playerCardNumberSum, dealerCardNumberSum) -> GameResultType.WIN
            isDraw(playerCardNumberSum, dealerCardNumberSum) -> GameResultType.DRAW
            else -> GameResultType.LOSE
        }
    }

    fun calculateWinMoney(gameResultType: GameResultType): Money {
        if (cards.isBlackjack()) return Money((bet.value * FACTOR_BLACKJACK_MONEY).toInt())
        return when (gameResultType) {
            GameResultType.WIN -> Money(bet.value * FACTOR_WIN_MONEY)
            GameResultType.DRAW -> Money(FACTOR_DRAW_MONEY)
            GameResultType.LOSE -> Money(bet.value * FACTOR_LOSE_MONEY)
        }
    }

    private fun isWin(playerCardNumberSum: Int, dealerCardNumberSum: Int): Boolean =
        playerCardNumberSum in (dealerCardNumberSum + 1)..BlackjackStage.WINNING_NUMBER || BlackjackStage.WINNING_NUMBER in playerCardNumberSum until dealerCardNumberSum

    private fun isDraw(playerCardNumberSum: Int, dealerCardNumberSum: Int): Boolean =
        playerCardNumberSum == dealerCardNumberSum || playerCardNumberSum > BlackjackStage.WINNING_NUMBER && dealerCardNumberSum > BlackjackStage.WINNING_NUMBER

    companion object {
        const val FACTOR_WIN_MONEY = 2
        const val FACTOR_DRAW_MONEY = 0
        const val FACTOR_LOSE_MONEY = -1
        const val FACTOR_BLACKJACK_MONEY = 1.5
    }
}
