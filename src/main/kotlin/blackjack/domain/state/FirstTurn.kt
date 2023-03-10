package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class FirstTurn(card: Card) : InTurn(Cards(setOf(card))) {

    override fun draw(card: Card): State {
        val newCards: Cards = cards + card
        return when {
            newCards.calculateScore().isBlackJack -> BlackJack(newCards)
            else -> Hit(newCards)
        }
    }
}
