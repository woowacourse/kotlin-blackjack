package blackjack.state

import blackjack.model.CardHolder
import blackjack.model.GameResult
import blackjack.model.Hand

class Blackjack(hand: Hand) : Finished(hand) {
    override fun calculate(opponent: CardHolder): GameResult {
        if (opponent.getState() is Blackjack) return GameResult(0, 0)
        return GameResult(1, 0)
    }
}
