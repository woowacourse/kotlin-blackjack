package blackjack.domain.state

import blackjack.domain.Dealer
import blackjack.domain.Hand
import blackjack.domain.Result
import blackjack.domain.card.Card

class Bust(override val hand: Hand) : Finished(hand) {
    override fun draw(card: Card): PlayingState {
        return this
    }

    override fun decideResult(dealer: Dealer): Result {
        return Result.LOSE
    }
}
