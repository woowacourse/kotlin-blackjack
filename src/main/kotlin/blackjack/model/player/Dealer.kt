package blackjack.model.player

import blackjack.model.card.Deck
import blackjack.model.card.Hand
import blackjack.model.game.ScoreCalculation
import blackjack.model.game.State

class Dealer(val hand: Hand) {
    var state = State.RUNNING

    fun draw() {
        if (judgeDraw()) hand.draw(Deck.dealCard())
    }

    private fun judgeDraw(): Boolean {
        return ScoreCalculation.calculate(hand) <= 16
    }
}
