package model

class DealerBuilder {
    private var hand: Hand = Hand(emptyList())
    fun hand(card: Card) {
        hand.add(card)
    }

    fun build(): Dealer = Dealer(hand)
}
