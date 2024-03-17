package blackjack.model.state

import blackjack.model.Card
import blackjack.model.Hand
import blackjack.model.Profit

class Hit(
    hand: Hand,
    profit: Profit
) : Progressing(hand, profit) {
    private var _hand: Hand = hand
    override val hand: Hand
        get() = _hand

    override fun getCard(card: Card): State {
        _hand += card
        return updateState(hand.calculate())
    }

    override fun hitOrStay(isHit: Boolean): State = this
}
