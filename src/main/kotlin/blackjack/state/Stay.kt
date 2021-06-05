package blackjack.state

import blackjack.domain.card.Card
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Hand
import blackjack.domain.gamer.Score
import blackjack.domain.result.GameResult

class Stay(hand: Hand) : Finish(hand) {
    override fun draw(card: Card): State {
        throw IllegalArgumentException("hit할 수 없는 상태입니다.")
    }

    override fun stay(): State {
        throw IllegalArgumentException("이미 stay 상태입니다.")
    }

    override fun isBust(): Boolean {
        return false
    }

    override fun isBlackjack(): Boolean {
        return false
    }

    override fun result(dealerScore: Score): GameResult {
        return GameResult.find(hand.totalScore(), dealerScore)
    }

    override fun earningRate(dealer: Dealer): Double {
        if (dealer.isBust() || hand.totalScore() > dealer.score()) {
            return 1.0
        }
        if (!dealer.isBust() && hand.totalScore() == dealer.score()) {
            return 0.0
        }
        return -1.0
    }
}