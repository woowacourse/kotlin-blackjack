package domain.state

import domain.card.Card
import domain.card.HandOfCards

class Hit(override val handOfCards: HandOfCards) : InProgress() {
    override fun nextState(card: Card): State {
        handOfCards.addCard(card)
        if (handOfCards.isBust()) return Bust(handOfCards)
        return this
    }
}

class DealerHit(override val handOfCards: HandOfCards) : InProgress() {
    override fun nextState(card: Card): State {
        handOfCards.addCard(card)
        if (handOfCards.isBust()) return Bust(handOfCards)
        return Stay(handOfCards)
    }
}
