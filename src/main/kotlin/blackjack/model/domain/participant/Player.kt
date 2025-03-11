package blackjack.model.domain.participant

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand

data class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())

    override fun canHit(): Boolean {
        return !hand.isBust()
    }

    override fun getInitCard(): List<Card> {
        return hand.cards
    }

    fun compareScores(dealer: Dealer): GameResult =
        when {
            hand.isBust() -> GameResult.Lose
            dealer.hand.isBust() -> GameResult.Win
            else -> compareNumber(sumCardNumber, dealer.sumCardNumber)
        }

    private fun compareNumber(
        target: Int,
        other: Int,
    ): GameResult =
        when {
            target < other -> GameResult.Lose
            target > other -> GameResult.Win
            else -> GameResult.Draw
        }
}
