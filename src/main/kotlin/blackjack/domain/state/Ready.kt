package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

class Ready(
    override val hand: Hand,
) : State {
    override fun draw(card: Card): State {
        val hand = Hand(listOf(card), hand.money)

        return Hit(hand)
    }

    override fun canDrawCard(): Boolean = false

    override fun profit(profitMoney: Int): Int = throw IllegalArgumentException()
}
