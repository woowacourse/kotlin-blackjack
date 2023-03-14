package blackjack.domain.state

import blackjack.domain.CardBunch

class Hit(override val hand: CardBunch) : ProgressAble(hand) {

    override fun returnCondition(hand: CardBunch): State {
        if (hand.isBurst()) return Burst(hand)
        return (Hit(hand))
    }

    override fun stay(): State = Stay(hand)
}
