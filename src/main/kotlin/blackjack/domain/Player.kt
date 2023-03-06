package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class Player(val name: String, override val cardBunch: CardBunch) : Participant {

    fun chooseWinner(dealerScore: Int): Consequence {
        val playerScore = cardBunch.getTotalScore()
        if (playerScore > MAX_SCORE_CONDITION) return Consequence.LOSE
        if (playerScore > dealerScore) return Consequence.WIN
        if (playerScore == dealerScore) return Consequence.DRAW
        if (dealerScore > MAX_SCORE_CONDITION) return Consequence.WIN
        return Consequence.LOSE
    }
}
