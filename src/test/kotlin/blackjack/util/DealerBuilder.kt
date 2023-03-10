package blackjack.util

import model.cards.Card
import model.cards.Hand
import model.participants.Dealer

class DealerBuilder {
    private var hand: Hand = Hand(emptyList())
    fun hand(card: Card) {
        hand.add(card)
    }

    fun build(): Dealer = Dealer(hand)
}
