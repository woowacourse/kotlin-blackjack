package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.CardBunchForState

class FirstTurn(override val hand: CardBunchForState) : State {
    fun draw(card: Card): State {
        val addedHand = CardBunchForState(hand.cards + card)
        if (addedHand.getTotalScore() == 21 && addedHand.cards.size == 2) return BlackJack(addedHand)
        return Hit(addedHand)
    }
}
