package blackjack.base

import blackjack.model.Card
import blackjack.model.Deck

abstract class BaseHolder {
    abstract val deck: Deck

    abstract fun takeCard(card: Card)
}
