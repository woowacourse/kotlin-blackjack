package blackjack.state

import blackjack.model.Hand

abstract class Running(hand: Hand) : Started(hand) {
    override fun isFinished(): Boolean = false
}
