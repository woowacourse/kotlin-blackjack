package blackjack.model.player

import blackjack.model.card.Hand
import blackjack.model.game.State

class Dealer(val hand: Hand) {
    val state: State
        get() =
            if (hand.isBust()) {
                State.Finished.Bust
            } else if (judgeDraw()) {
                State.Running.Hit
            } else if (judgeBlackJack()) {
                State.Finished.BlackJack
            } else {
                State.Finished.Stay
            }

    fun judgeDraw(): Boolean {
        return hand.totalScore <= 16
    }

    fun judgeBlackJack(): Boolean {
        return hand.totalScore == 21
    }
}
