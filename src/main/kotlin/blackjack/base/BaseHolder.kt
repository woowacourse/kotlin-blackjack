package blackjack.base

import blackjack.model.Card
import blackjack.model.Hand

abstract class BaseHolder(
    hand: Hand = Hand(),
) : BaseHuman() {
    private var _hand: Hand = hand
    val hand: Hand
        get() = _hand


    fun takeCard(card: Card) {
        _hand += card
    }
}
