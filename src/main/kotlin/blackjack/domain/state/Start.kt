package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Start(private val cards: Cards = Cards()) : State {
    constructor(vararg cards: Card) : this(Cards(*cards))

    fun draw(card: Card): State {
        cards.add(card)

        if (cards.isStartLimitSize()) return next()
        return Start(cards)
    }

    private fun next(): State {
        if (cards.isBlackjack()) return Blackjack(cards)
        return Hit(cards)
    }
}
