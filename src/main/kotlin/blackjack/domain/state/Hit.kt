package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.Money

class Hit(override val hand: Hand, override val bettingMoney: Money?) : Running(hand, bettingMoney) {
    override fun draw(card: Card): State {
        val nextHand = hand + card
        return when {
            nextHand.isBlackjack() -> Blackjack(nextHand, bettingMoney)
            nextHand.isBust() -> Bust(nextHand, bettingMoney)
            else -> Hit(nextHand, bettingMoney)
        }
    }
}
