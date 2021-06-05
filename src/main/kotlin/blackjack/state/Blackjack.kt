package blackjack.state

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Hand
import blackjack.domain.gamer.Score
import blackjack.domain.result.GameResult

class Blackjack(hand: Hand) : Finish(hand) {
    override fun isBust(): Boolean {
        return false
    }

    override fun isBlackjack(): Boolean {
        return true
    }

    override fun result(dealerScore: Score): GameResult {
        return GameResult.WIN
    }

    override fun earningRate(dealer: Dealer) =
        when (dealer.isBlackjack()) {
            true -> 0.0
            false -> 1.5
        }
}