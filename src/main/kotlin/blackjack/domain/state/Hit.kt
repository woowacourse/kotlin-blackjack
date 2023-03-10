package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Hit(cards: Cards) : InTurn(cards) {

    override fun draw(card: Card): State {
        val newCards: Cards = cards + card
        return when {
            newCards.calculateScore().isBlackJack -> Stay(newCards)
            newCards.calculateScore().isBust -> Bust(newCards)
            else -> Hit(cards + card)
        }
    }
}
