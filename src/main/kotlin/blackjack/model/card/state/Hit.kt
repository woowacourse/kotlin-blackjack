package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand

class Hit(cardHand: CardHand) : Decide(cardHand) {
    override fun draw(card: Card): CardHandState {
        cardHand.addNewCard(card)
        if (cardHand.isBust) return Bust(cardHand)
        return Hit(cardHand)
    }

    override fun stay(): CardHandState = Stay(cardHand)
}
