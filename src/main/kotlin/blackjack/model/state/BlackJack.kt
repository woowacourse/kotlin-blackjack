package blackjack.model.state

import blackjack.model.domain.Hand
import blackjack.model.entitiy.Card

data class BlackJack(override val hand: Hand) : State {
    override fun draw(card: Card): State {
        throw IllegalArgumentException()
    }

    override fun profit(dealerTotal: Int): Int {
        return hand.calculateProfit(1.5).toInt()
    }
}
