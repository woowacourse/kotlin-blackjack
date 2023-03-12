package blackjack.domain.state

import blackjack.domain.CardBunchForState

class DealerHit(override val hand: CardBunchForState) : ProgressAble(hand) {
    override fun returnCondition(hand: CardBunchForState): State {
        if (hand.isBurst()) return Burst(hand)
        if (hand.isDealerBurst()) return Stay(hand)
        return (DealerHit(hand))
    }
}
