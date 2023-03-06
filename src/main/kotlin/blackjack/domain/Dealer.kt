package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class Dealer(override val cardBunch: CardBunch) : Participant {
    override fun isOverCondition(): Boolean = cardBunch.getTotalScore() > ADD_CARD_CONDITION

    fun compareScore(playerScore: Int): Consequence {
        val dealerScore = cardBunch.getTotalScore()
        if (playerScore > MAX_SCORE_CONDITION) return Consequence.LOSE
        if (playerScore > dealerScore) return Consequence.WIN
        if (playerScore == dealerScore) return Consequence.DRAW
        if (dealerScore > MAX_SCORE_CONDITION) return Consequence.WIN
        return Consequence.LOSE
    }

    fun addCard(card: Card) {
        cardBunch.addCard(card)
    }

    companion object {
        private const val ADD_CARD_CONDITION = 16
    }
}
