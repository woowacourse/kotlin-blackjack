package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.CardBunch

abstract class ProgressAble(override val hand: CardBunch) : State {
    override fun draw(card: Card): State {
        val addedHand = CardBunch(hand.cards + card)
        return returnCondition(addedHand)
    }

    abstract fun returnCondition(hand: CardBunch): State
}
