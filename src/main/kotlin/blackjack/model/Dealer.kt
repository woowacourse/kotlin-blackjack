package blackjack.model

import blackjack.base.BaseHolder

class Dealer(deck: Deck = Deck()) : BaseHolder() {
    private var _deck: Deck = deck
    override val deck: Deck
        get() = _deck

    override fun takeCard(card: Card) {
        _deck += card
    }
}
