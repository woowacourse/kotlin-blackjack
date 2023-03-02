package blackjack.domain

class Player(val name: String, override val cardBunch: CardBunch) : Participant {

    lateinit var consequence: Consequence
    fun chooseWinner(score: Int) {
        val playerScore = cardBunch.getTotalScore()
        consequence = if (playerScore > 21) {
            Consequence.LOSE
        } else if (playerScore > score) {
            Consequence.WIN
        } else if (playerScore == score) {
            Consequence.DRAW
        } else {
            Consequence.LOSE
        }
    }
}
