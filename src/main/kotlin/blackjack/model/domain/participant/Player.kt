package blackjack.model.domain.participant

import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Hand
import blackjack.model.domain.card.Status

data class Player(override val name: String) : Participants() {
    override val hand: Hand = Hand(mutableListOf())

    override fun canHit(): Boolean {
        return hand.status == Status.BUST
    }

    fun compareScores(
        status: Status,
        number: Int,
    ): GameResult =
        when {
            hand.status == Status.BUST -> GameResult.Lose
            status == Status.BUST -> GameResult.Win
            status == Status.BLACKJACK && hand.status == Status.BLACKJACK -> GameResult.Draw
            status == Status.BLACKJACK -> GameResult.Lose
            hand.status == Status.BLACKJACK -> GameResult.BlackjackWin
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
