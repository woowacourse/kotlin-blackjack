package blackjack.model.game

import blackjack.model.card.Hand

class ScoreCalculation {
    fun calculate(hand: Hand): Int {
        return hand.cards.sumOf { card -> card.denomination.score }
    }
}
