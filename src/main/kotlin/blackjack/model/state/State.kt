package blackjack.model.state

import blackjack.model.domain.Hand
import blackjack.model.entitiy.Card

interface State {
    val hand: Hand
    fun draw(card: Card): State

    fun profit(dealerTotal: Int): Int
}
