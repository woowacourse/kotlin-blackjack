package blackjack.model.domain.participant

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Hand

data class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())

    override fun canHit(): Boolean {
        return hand.isBust()
    }

    fun compareScores(
        isDealerBust: Boolean,
        number: Int,
    ): GameResult =
        when {
            hand.isBust() -> GameResult.Lose
            isDealerBust -> GameResult.Win
            else -> GameResult.compare(sumCardNumber, number)
        }
}
