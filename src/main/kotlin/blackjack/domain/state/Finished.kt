package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand

abstract class Finished(
    override val hand: Hand,
) : State {
    override fun draw(card: Card) = throw IllegalArgumentException()
}
