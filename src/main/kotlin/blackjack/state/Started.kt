package blackjack.state

import blackjack.model.Hand

abstract class Started(private val hand: Hand) : BlackjackState {
    override fun hand(): Hand = hand
}
