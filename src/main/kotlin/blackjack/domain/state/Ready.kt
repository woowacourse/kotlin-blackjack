package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

class Ready(override val hand: Hand = Hand(emptyList())) : PlayingState {
    override fun draw(card: Card): PlayingState {
        hand.addCard(card)
        return when {
            hand.isBlackjack() -> Blackjack(hand)
            hand.isBust() -> Bust(hand)
            else -> Hit(hand)
        }
    }
}
