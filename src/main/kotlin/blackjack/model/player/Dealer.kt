package blackjack.model.player

import blackjack.model.card.Deck
import blackjack.model.card.Hand
import blackjack.model.game.ScoreCalculation

class Dealer(val hand: Hand) {
    fun draw() {
        if (judgeDraw()) hand.draw(Deck.dealCard())
    }

    private fun judgeDraw(): Boolean {
        return ScoreCalculation.calculate(hand) <= 16
    }
}
