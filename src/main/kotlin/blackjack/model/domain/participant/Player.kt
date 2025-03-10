package blackjack.model.domain.participant

import blackjack.model.domain.GameResult
import blackjack.model.domain.GameResult.Companion.isBust
import blackjack.model.domain.card.Hand

data class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())
    override var status: GameResult = GameResult.None

    override fun canHit(): Boolean {
        return isBust(sumCardNumber) != GameResult.Lose
    }

    fun compareScores(number: Int) {
        if (status != GameResult.Lose) {
            status = GameResult.compare(sumCardNumber, number)
        }
    }
}
