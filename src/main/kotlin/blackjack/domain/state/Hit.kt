package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

class Hit(override val hand: Hand) : PlayingState {
    override fun draw(card: Card): PlayingState {
        hand.addCard(card)
        return when {
            hand.isBust() -> Bust(hand)
            hand.isBlackjack() -> Blackjack(hand)
            else -> this
        }
    }

    fun stay(): PlayingState {
        return Stay(hand)
    }
}
