package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class FirstTurn private constructor(cards: Cards) : InTurn(cards) {
    override val ratio: Double = 0.0
    constructor() : this(Cards())

    override fun draw(card: Card): State {
        val newCards: Cards = cards + card
        return when {
            newCards.size == 1 -> FirstTurn(newCards)
            newCards.calculateScore().isBlackJack -> BlackJack(newCards)
            else -> Hit(newCards)
        }
    }
}
