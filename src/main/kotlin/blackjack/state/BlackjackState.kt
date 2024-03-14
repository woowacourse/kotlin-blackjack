package blackjack.state

import blackjack.model.Card
import blackjack.model.Hand

interface BlackjackState {
    fun draw(card: Card): BlackjackState

    fun stay(): BlackjackState

    fun hand(): Hand

    fun isFinished(): Boolean
}
