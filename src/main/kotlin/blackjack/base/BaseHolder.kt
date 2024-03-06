package blackjack.base

import blackjack.model.Card
import blackjack.model.Deck

abstract class BaseHolder(deck: Deck = Deck()) {
    abstract val name: String

    private var _deck: Deck = deck
    val deck: Deck
        get() = _deck

    fun takeCard(card: Card) {
        _deck += card
    }
}
