package blackjack.domain.state

import blackjack.domain.CardBunchForState

class Hit(override val hand: CardBunchForState) : ProgressAble(hand) {

    override fun returnCondition(hand: CardBunchForState): State {
        if (hand.isBurst()) return Burst(hand)
        return (Hit(hand))
    }

    fun stay(): State = Stay(hand)
}
