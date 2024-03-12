package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand

class InitialState(cardHand: CardHand) : Decide(cardHand) {
    override fun draw(card: Card): CardsState {
        if (cardHand.hand.size < INITIAL_CARD_COUNT) {
            cardHand.addNewCard(card)
            return InitialState(cardHand)
        }
        return Hit(cardHand)
    }

    override fun stay(): CardsState = throw IllegalStateException("예기치 못한 오류")

    companion object {
        private const val INITIAL_CARD_COUNT = 2
    }
}
