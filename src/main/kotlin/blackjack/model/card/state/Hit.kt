package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.config.GameRule.BLACK_JACK_SCORE

class Hit(cardHand: CardHand) : Decide(cardHand) {
    override fun draw(card: Card): CardHandState {
        cardHand.addNewCard(card)
        if (isBust()) return Bust(cardHand)
        return Hit(cardHand)
    }

    override fun stay(): CardHandState = Stay(cardHand)

    private fun isBust(): Boolean = (getCardHandScore() > BLACK_JACK_SCORE)
}
