package blackjack.state

import blackjack.model.Card
import blackjack.model.CardHolder
import blackjack.model.GameResult
import blackjack.model.Hand

abstract class Finished(private val hand: Hand) : Started(hand) {
    override fun draw(card: Card): BlackjackState = this

    override fun isFinished(): Boolean = true

    override fun stay(): BlackjackState = Stay(hand)

    abstract fun calculate(opponent: CardHolder): GameResult
}
