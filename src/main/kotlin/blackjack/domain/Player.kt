package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class Player(val name: String, override val cardBunch: CardBunch) : Participant {

    lateinit var consequence: Consequence
    fun chooseWinner(score: Int) {
        val playerScore = cardBunch.getTotalScore()
        consequence = if (playerScore > MAX_SCORE_CONDITION) {
            Consequence.LOSE
        } else if (playerScore > score) {
            Consequence.WIN
        } else if (playerScore == score) {
            Consequence.DRAW
        } else if (score > MAX_SCORE_CONDITION) {
            Consequence.WIN
        } else {
            Consequence.LOSE
        }
    }
}
