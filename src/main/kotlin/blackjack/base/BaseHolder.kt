package blackjack.base

import blackjack.model.Card

abstract class BaseHolder {
    abstract val cards: List<Card>

    abstract fun takeCard(card: Card)
}
