package blackjack.model.card

import blackjack.model.game.ScoreCalculation

class Hand(val cards: MutableList<Card>) {
    var totalScore = 0

    fun draw(card: Card) {
        cards.add(card)
        totalScore = ScoreCalculation.calculate(this)
    }
}
