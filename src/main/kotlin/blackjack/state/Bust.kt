package blackjack.state

import blackjack.model.CardHolder
import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Hand

class Bust(hand: Hand) : Finished(hand) {
    override fun calculate(opponent: CardHolder): GameResult {
        if (opponent is Dealer && opponent.getState() is Bust) return GameResult(1, 0)
        return GameResult(0, 1)
    }
}
