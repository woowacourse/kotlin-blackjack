package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.config.GameRule.BLACK_JACK_SCORE

class Hit(cardHand: CardHand) : Decide(cardHand) {
    override fun draw(card: Card): CardHandState {
        cardHand.addNewCard(card)
        if (cardHand.calculateScore() > BLACK_JACK_SCORE) return Bust(cardHand)
        return Hit(cardHand)
    }

    override fun stay(): CardHandState {
        if (cardHand.calculateScore() == BLACK_JACK_SCORE) return BlackJack(cardHand)
        return Stay(cardHand)
    }
}
