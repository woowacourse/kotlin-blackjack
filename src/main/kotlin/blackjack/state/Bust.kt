package blackjack.state

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Hand
import blackjack.domain.gamer.Score
import blackjack.domain.result.GameResult

class Bust(hand: Hand) : Finish(hand) {
    override fun isBust(): Boolean {
        return true
    }

    override fun isBlackjack(): Boolean {
        return false
    }

    override fun result(dealerScore: Score): GameResult {
        return GameResult.LOSE
    }

    override fun earningRate(dealer: Dealer): Double {
        return -1.0
    }
}