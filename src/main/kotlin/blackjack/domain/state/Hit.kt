package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.CardBunchForState

class Hit(override val hand: CardBunchForState) : State {
    fun draw(card: Card): State {
        val addedHand = CardBunchForState(hand.cards + card)
        if (addedHand.isBurst()) return Burst(addedHand)
        return (Hit(addedHand))
    }
}
