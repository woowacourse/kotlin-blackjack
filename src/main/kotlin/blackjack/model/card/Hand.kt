package blackjack.model.card

import blackjack.model.game.ScoreCalculation

class Hand(val cards: MutableList<Card>) {
    var aceCount = 0
    var totalScore = ScoreCalculation.calculate(this)

    fun draw(card: Card) {
        cards.add(card)
        totalScore = ScoreCalculation.calculate(this)
        if (card.denomination == Denomination.ACE) aceCount++
    }

    override fun toString(): String {
        return cards.joinToString(", ")
    }
}
