package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class Dealer(override val cardBunch: CardBunch) : Participant {
    override fun isOverCondition(): Boolean = cardBunch.getTotalScore() > ADD_CARD_CONDITION

    fun compareScore(playerScore: Int, isPlayerBlackjack: Boolean): Consequence {
        val dealerScore = cardBunch.getTotalScore()
        val isDealerBlackJack = cardBunch.isBlackJack()
        return when {
            isPlayerBlackjack && !isDealerBlackJack -> Consequence.WIN
            !isPlayerBlackjack && isDealerBlackJack -> Consequence.LOSE
            playerScore > MAX_SCORE_CONDITION -> Consequence.LOSE
            playerScore > dealerScore -> Consequence.WIN
            playerScore == dealerScore -> Consequence.DRAW
            dealerScore > MAX_SCORE_CONDITION -> Consequence.WIN
            else -> Consequence.LOSE
        }
    }

    companion object {
        private const val ADD_CARD_CONDITION = 16
    }
}
