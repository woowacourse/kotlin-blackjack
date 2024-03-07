package blackjack.model.card

import blackjack.model.game.ScoreCalculation

class Hand(val cards: MutableList<Card>) {
    var aceCount = 0

    init {
        require(cards.size >= MINIMUM_CARDS_COUNT) { "손패는 2장 이상이어야 합니다." }
    }

    fun draw(card: Card) {
        cards.add(card)
        if (card.denomination == Denomination.ACE) aceCount++
    }

    private fun isBust(): Boolean {
        return ScoreCalculation.calculate(Hand(cards)) > 21
    }

    private fun isBlackjack(): Boolean {
        return ScoreCalculation.calculate(Hand(cards)) == 21
    }

    companion object {
        const val MINIMUM_CARDS_COUNT = 2
    }
}
