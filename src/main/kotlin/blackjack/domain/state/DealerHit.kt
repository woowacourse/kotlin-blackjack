package blackjack.domain.state

import blackjack.domain.CardBunch

class DealerHit(override val hand: CardBunch) : ProgressAble(hand) {
    override fun returnCondition(hand: CardBunch): State {
        if (hand.isBurst()) return Burst(hand)
        if (hand.isDealerBurst()) return Stay(hand)
        return (DealerHit(hand))
    }
}
