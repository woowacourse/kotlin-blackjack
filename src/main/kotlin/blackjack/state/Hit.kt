package blackjack.state

import blackjack.domain.card.Card
import blackjack.domain.gamer.Hand

class Hit(hand: Hand = Hand()) : Running(hand) {
    override fun draw(card: Card): State {
        hand.addCard(card)
        if (hand.isBust()) {
            return Bust(hand)
        }
        return Hit(hand)
    }

    override fun isBust(): Boolean {
        return false
    }

    override fun isBlackjack(): Boolean {
        return false
    }
}