package blackjack.model.state

import blackjack.model.Card
import blackjack.model.Hand

class Hit(hand: Hand) : Progressing(hand) {
    private var _hand: Hand = hand
    override val hand: Hand
        get() = _hand

    override fun getCard(card: Card): State {
        _hand += card
        return updateState(hand.calculate())
    }

    override fun hitOrStay(isHit: Boolean): State = this
}
