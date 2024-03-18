package blackjack.model.state

import blackjack.model.domain.Hand
import blackjack.model.entitiy.Card

data class Ready(override val hand: Hand) : State {

    override fun draw(card: Card): State {
        val hand = Hand(setOf(card), hand.profit)

        return Hit(hand)
    }

    override fun profit(dealerTotal: Int): Int {
        throw IllegalArgumentException()
    }
}
