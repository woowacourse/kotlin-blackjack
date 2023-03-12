package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.CardBunch

class FirstTurn(override val hand: CardBunch) : State {
    fun draw(card: Card): State {
        hand.addCard(card)
        if (hand.getTotalScore() == 21 && hand.cards.size == 2) return BlackJack(hand)
        return Hit(hand)
    }
}
