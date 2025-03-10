package blackjack.domain.person

class Dealer(hand: Hand) : Person(hand) {
    constructor() : this(hand = Hand())
}
