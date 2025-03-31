package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

interface State {
    val hand: Hand

    fun draw(card: Card): State

    fun canDrawCard(): Boolean

    fun profit(state: State): Double
}
