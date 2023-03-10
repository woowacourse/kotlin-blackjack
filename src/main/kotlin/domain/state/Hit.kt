package domain.state

import domain.card.Card
import domain.card.HandOfCards

class Hit(override val handOfCards: HandOfCards) : InProgress() {
    override fun nextState(draw: () -> Card): State {
        handOfCards.addCard(draw())
        if (handOfCards.isBust()) return Bust(handOfCards)
        return this
    }
}
