package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.CardBunchForState

class Hit(override val hand: CardBunchForState) : State {
    override fun draw(card: Card): State {
        val addedHand = CardBunchForState(hand.cards + card)
        if (addedHand.isBurst()) return Burst(addedHand)
        return (Hit(addedHand))
    }

    fun stay(): State = Stay(hand)
}
