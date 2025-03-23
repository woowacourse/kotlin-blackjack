package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

class Ready(
    override val hand: Hand = Hand(emptyList()),
) : State {
    override fun draw(card: Card): State {
        hand.addCard(card)
        return when {
            hand.hasBlackjack() -> Blackjack(hand)
            else -> Hit(hand)
        }
    }

    override fun canDrawCard(): Boolean = false

    override fun profit(state: State): Double = 0.0
}
