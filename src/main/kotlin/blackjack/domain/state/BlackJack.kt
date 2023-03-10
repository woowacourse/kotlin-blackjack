package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class BlackJack(cards: Cards = Cards()) : EndTurn(cards) {
    override val ratio: Double = 1.5
    constructor(cards: List<Card>) : this(Cards(cards.toSet()))
    constructor(vararg cards: Card) : this(Cards(cards.toSet()))
}
