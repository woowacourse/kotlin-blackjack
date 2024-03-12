package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand

class Hit(cardHand: CardHand) : Decide(cardHand) {
    override fun draw(card: Card): CardsState {
        return Hit(cardHand)
    }

    override fun stay(): CardsState {
        return Hit(cardHand)
    }
}
