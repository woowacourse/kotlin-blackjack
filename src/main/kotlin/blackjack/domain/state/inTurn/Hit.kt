package blackjack.domain.state.inTurn

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.Outcome
import blackjack.domain.state.State
import blackjack.domain.state.endTurn.Bust
import blackjack.domain.state.endTurn.Stay

class Hit(cards: Cards) : InTurn(cards) {
    override fun draw(card: Card): State {
        val newCards: Cards = cards + card
        return when {
            newCards.calculateScore().isBlackJack -> Stay(newCards)
            newCards.calculateScore().isBust -> Bust(newCards)
            else -> Hit(cards + card)
        }
    }

    override fun matchWith(otherState: State): Outcome = Stay(cards).matchWith(otherState)
}
