package blackjack.domain.state

import blackjack.domain.CardBunch

class DealerFirstTurn(override val hand: CardBunch) : ProgressAble(hand) {

    override fun returnCondition(hand: CardBunch): State {
        if (hand.getTotalScore() == 21 && hand.cards.size == 2) return BlackJack(hand)
        if (hand.isDealerBurst()) return Stay(hand)
        return DealerHit(hand)
    }
}
