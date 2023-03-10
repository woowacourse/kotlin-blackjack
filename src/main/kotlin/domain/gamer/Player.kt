package domain.gamer

import domain.gamer.cards.Cards
import domain.gamer.cards.Cards.Companion.CARD_SUM_MAX_VALUE
import domain.judge.Result

class Player(val name: String, cards: Cards) : Participant(cards) {

    fun judgeResult(dealerCards: Cards): Result {
        val playerSum = cards.calculateCardSum()
        val dealerSum = dealerCards.calculateCardSum()

        return when {
            playerSum > CARD_SUM_MAX_VALUE ||
                (dealerCards.checkBlackjack() && !cards.checkBlackjack()) ||
                dealerSum.checkPlayerLossCondition(
                    playerSum
                ) -> Result.LOSS

            playerSum > dealerSum || dealerSum > CARD_SUM_MAX_VALUE ||
                (cards.checkBlackjack() && !dealerCards.checkBlackjack()) -> Result.WIN

            else -> Result.DRAW
        }
    }

    private fun Int.checkPlayerLossCondition(playerSum: Int) = this > playerSum && this <= CARD_SUM_MAX_VALUE
}
