package domain.state

import domain.card.Card
import domain.card.HandOfCards

class PlayerFirstTurn(override val handOfCards: HandOfCards) : InProgress() {
    override fun nextState(card: Card): State {
        handOfCards.addCard(card)
        return when {
            handOfCards.isBlackJack() -> BlackJack(handOfCards)
            handOfCards.size < 2 -> PlayerFirstTurn(handOfCards)
            else -> PlayerHit(handOfCards)
        }
    }
}

class DealerFirstTurn(override val handOfCards: HandOfCards) : InProgress() {
    override fun nextState(card: Card): State {
        handOfCards.addCard(card)
        return when {
            handOfCards.isBlackJack() -> BlackJack(handOfCards)
            handOfCards.isDealerStay() -> Stay(handOfCards)
            handOfCards.size < 2 -> DealerFirstTurn(handOfCards)
            else -> DealerHit(handOfCards)
        }
    }
}
