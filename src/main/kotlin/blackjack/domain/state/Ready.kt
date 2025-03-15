package blackjack.domain.state

import blackjack.domain.Hand
import blackjack.domain.card.Card

class Ready(val hand: Hand) : PlayingState {
    override fun draw(card: Card): PlayingState {
        hand.addCard(card)
        return Hit(hand)
    }
}
