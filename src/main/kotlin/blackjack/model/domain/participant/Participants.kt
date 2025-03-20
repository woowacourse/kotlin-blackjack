package blackjack.model.domain.participant

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Card
import blackjack.model.domain.card.Hand

abstract class Participants() {
    abstract val name: String
    abstract val hand: Hand

    val sumCardNumber: Int get() = hand.getSumNumber()
    val cardDeck get() = hand.cards.toList()

    fun receiveCard(cards: List<Card>) {
        hand.append(cards)
    }

    abstract fun showStartCards(): List<Card>

    abstract fun canHit(): Boolean

    fun compareScores(otherHand: Hand): GameResult =
        when {
            otherHand.isBlackJack() && hand.isBlackJack() -> GameResult.Draw
            hand.isBlackJack() -> GameResult.BlackjackWin
            hand.isBust() -> GameResult.Lose
            otherHand.isBust() -> GameResult.Win
            otherHand.isBlackJack() -> GameResult.Lose
            else -> compare(sumCardNumber, otherHand.getSumNumber())
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
