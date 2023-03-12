package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.CardBunchForState

abstract class ProgressAble(override val hand: CardBunchForState) : State {
    override fun draw(card: Card): State {
        val addedHand = CardBunchForState(hand.cards + card)
        return returnCondition(addedHand)
    }

    abstract fun returnCondition(hand: CardBunchForState): State
}
