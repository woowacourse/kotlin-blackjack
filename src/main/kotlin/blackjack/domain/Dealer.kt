package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class Dealer(override val cardBunch: CardBunch) : Participant {
    override fun isOverCondition(): Boolean = cardBunch.getTotalScore() > ADD_CARD_CONDITION

    fun compareScore(playerScore: Int): Consequence {
        val dealerScore = cardBunch.getTotalScore()
        return when {
            playerScore > MAX_SCORE_CONDITION -> Consequence.LOSE
            playerScore > dealerScore -> Consequence.WIN
            playerScore == dealerScore -> Consequence.DRAW
            dealerScore > MAX_SCORE_CONDITION -> Consequence.WIN
            else -> Consequence.LOSE
        }
    }

    fun addCard(card: Card) {
        cardBunch.addCard(card)
    }

    companion object {
        private const val ADD_CARD_CONDITION = 16
    }
}
