package domain.participants

import domain.card.Cards
import domain.judge.Result

class Dealer(val ownCards: Cards, val name: String = "딜러") {

    fun checkOverCondition(): Boolean = ownCards.calculateCardSum() > CARD_PICK_CONDITION

    fun judgePlayerResult(player: Cards): Result {
        val playerSum = player.calculateCardSum()
        val dealerSum = ownCards.calculateCardSum()

        return when {
            player.checkBlackJack() && ownCards.checkBlackJack() -> Result.DRAW
            player.checkBlackJack() -> Result.BLACKJACK_WIN
            playerSum > CARD_SUM_MAX_VALUE -> Result.LOSS
            dealerSum > CARD_SUM_MAX_VALUE -> Result.WIN
            dealerSum > playerSum -> Result.LOSS
            playerSum == CARD_SUM_MAX_VALUE && dealerSum == CARD_SUM_MAX_VALUE -> Result.LOSS
            playerSum > dealerSum -> Result.WIN
            else -> Result.DRAW
        }
    }

    companion object {
        private const val CARD_PICK_CONDITION = 16
        const val CARD_SUM_MAX_VALUE = 21
    }
}
