package blackjack.state

import blackjack.domain.gamer.Hand

fun generateInitialState(hand: Hand): State {
    if (hand.isBlackjack()) {
        return Blackjack(hand)
    }
    return Hit(hand)
}