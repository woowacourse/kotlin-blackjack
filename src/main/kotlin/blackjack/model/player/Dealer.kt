package blackjack.model.player

import blackjack.model.card.Hand
import blackjack.model.game.ScoreCalculation
import blackjack.model.game.State

class Dealer(val hand: Hand) {
    var state: State = State.Running.Hit

    fun judgeDraw(): Boolean {
        return ScoreCalculation.calculate(hand) <= 16
    }
}
