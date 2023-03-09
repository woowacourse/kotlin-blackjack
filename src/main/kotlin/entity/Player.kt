package entity

import model.CardFactory

class Player(val name: Name, private val bet: Money = Money(0), val cards: Cards = Cards()) : User {
    override fun isDistributable(): Boolean = cards.sumOfNumbers() < WINNING_NUMBER

    fun distribute(
        cardFactory: CardFactory,
        distributeCondition: (player: Player) -> Boolean,
        printPlayerStatus: (player: Player) -> Unit
    ) {
        while (isDistributable() && distributeCondition(this)) {
            cards.addCards(cardFactory.generate(User.SINGLE_DISTRIBUTE_COUNT))
            printPlayerStatus(this)
        }
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
        if (cards.isBlackjack()) return bet * FACTOR_BLACKJACK_MONEY
        return when (gameResultType) {
            GameResultType.WIN -> bet * FACTOR_WIN_MONEY
            GameResultType.DRAW -> bet * FACTOR_DRAW_MONEY
            GameResultType.LOSE -> bet * FACTOR_LOSE_MONEY
        }
    }

    private fun isWin(playerCardNumberSum: Int, dealerCardNumberSum: Int): Boolean {
        val isGreaterThanDealer = playerCardNumberSum in (dealerCardNumberSum + 1)..WINNING_NUMBER
        val isDealerBustButPlayer = WINNING_NUMBER in playerCardNumberSum until dealerCardNumberSum
        return isGreaterThanDealer || isDealerBustButPlayer
    }

    private fun isDraw(playerCardNumberSum: Int, dealerCardNumberSum: Int): Boolean {
        val isEqualWithDealer = playerCardNumberSum == dealerCardNumberSum
        val isPlayerBust = playerCardNumberSum > WINNING_NUMBER
        val isDealerBust = dealerCardNumberSum > WINNING_NUMBER
        return isEqualWithDealer || (isPlayerBust && isDealerBust)
    }

    companion object {
        const val FACTOR_WIN_MONEY = 2
        const val FACTOR_DRAW_MONEY = 0
        const val FACTOR_LOSE_MONEY = -1
        const val FACTOR_BLACKJACK_MONEY = 1.5
        const val WINNING_NUMBER = 21
    }
}
