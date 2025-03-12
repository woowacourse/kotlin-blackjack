package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

data class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())

    override fun canHit(): Boolean {
        return !hand.isBust()
    }

    override fun getInitCard(): List<Card> {
        return hand.cards.take(2)
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
