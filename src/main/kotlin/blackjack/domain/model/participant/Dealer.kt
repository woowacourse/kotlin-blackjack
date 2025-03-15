package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Hand

class Dealer(
    name: String = DEFAULT_NAME,
    hand: Hand = Hand(),
) : Participant(name, hand) {
    override fun showFirstHand(): List<Card> = listOf(handCards().first())

    override fun compareTo(opponent: Participant): GameResult {
        if (hand.isBust() && opponent.hand.isNotBust()) {
            return GameResult.LOSE
        }
        return super.compareTo(opponent)
    }

    override fun isDrawable(): Boolean = !hand.isMoreThan(DEALER_DRAW_CONDITION)

    companion object {
        private const val DEFAULT_NAME = "딜러"
        private const val DEALER_DRAW_CONDITION = 16
    }
}
