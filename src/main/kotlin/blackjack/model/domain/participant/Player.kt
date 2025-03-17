package blackjack.model.domain.participant

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand

data class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())

    override fun canHit(): Boolean {
        return hand.isBust()
    }

    override fun showInitCards(): List<Card> {
        return hand.cards
    }

    fun compareScores(
        otherHand: Hand,
        number: Int,
    ): GameResult =
        when {
            otherHand.isBlackJack() && hand.isBlackJack() -> GameResult.Draw
            hand.isBlackJack() -> GameResult.BlackjackWin
            hand.isBust() -> GameResult.Lose
            otherHand.isBust() -> GameResult.Win
            otherHand.isBlackJack() -> GameResult.Lose
            else -> compare(sumCardNumber, number)
        }

    private fun compare(
        target: Int,
        other: Int,
    ): GameResult {
        if (target < other) {
            return GameResult.Lose
        } else if (target > other) {
            return GameResult.Win
        }
        return GameResult.Draw
    }
}
