package domain.state

import domain.card.Card
import domain.card.HandOfCards

class FirstTurn(card1: Card, card2: Card) : InProgress() {
    override val handOfCards = HandOfCards(card1, card2)

    override fun nextState(draw: () -> Card): State {
        if (handOfCards.isBlackJack()) return BlackJack(handOfCards)
        handOfCards.addCard(draw())
        if (handOfCards.isBust()) return Bust(handOfCards)
        return Hit(handOfCards)
    }
}
