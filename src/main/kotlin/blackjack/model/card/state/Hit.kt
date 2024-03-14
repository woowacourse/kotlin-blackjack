package blackjack.model.card.state

import blackjack.model.card.Card
import blackjack.model.card.CardHand
import blackjack.model.result.Score

class Hit(cardHand: CardHand) : Decide(cardHand) {
    override fun draw(card: Card): CardsState {
        cardHand.addNewCard(card)
        if (cardHand.calculateScore() > Score(21)) return Bust(cardHand)
        return Hit(cardHand)
    }

    override fun stay(): CardsState {
        if (cardHand.calculateScore() == Score(21)) return BlackJack(cardHand)
        return Stay(cardHand)
    }
}
