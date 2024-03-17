package blackjack.model.state

import blackjack.model.Card
import blackjack.model.Hand
import blackjack.model.Profit

sealed class Finished(
    override val hand: Hand,
    override val profit: Profit,
) : State, CompareImpl {
    override fun getCard(card: Card): State = this

    override fun updateState(totalPoint: Int): State = this

    override fun hitOrStay(isHit: Boolean): State = this
}
