package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

abstract class Participants() {
    abstract val name: String
    abstract val hand: Hand

    val sumCardNumber: Int get() = hand.getSumNumber()
    val cardDeck get() = hand.cards.toList()

    fun receiveCard(cards: List<Card>) {
        hand.append(cards)
    }

    fun compareScores(participant: Participants): GameResult =
        when {
            hand.isBust() -> GameResult.Lose
            participant.hand.isBust() -> GameResult.Win
            participant.hand.isBlackjack() && !hand.isBlackjack() -> GameResult.Lose
            !participant.hand.isBlackjack() && hand.isBlackjack() -> GameResult.Win
            else -> compareNumber(sumCardNumber, participant.sumCardNumber)
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

    abstract fun canHit(): Boolean

    abstract fun getInitCard(): List<Card>
}
