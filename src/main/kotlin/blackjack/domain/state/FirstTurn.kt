package blackjack.domain.state

import blackjack.domain.CardBunchForState

class FirstTurn(override val hand: CardBunchForState) : ProgressAble(hand) {

    override fun returnCondition(hand: CardBunchForState): State {
        if (hand.getTotalScore() == 21 && hand.cards.size == 2) return BlackJack(hand)
        return Hit(hand)
    }
}