package blackjack.model.card

class Hand(val cards: MutableList<Card>) {
    var aceCount = 0
    var totalScore = 0

    fun draw(card: Card) {
        cards.add(card)
        totalScore += card.denomination.score
        if (card.denomination == Denomination.ACE) aceCount++
    }

    private fun isBust(): Boolean {
        return totalScore > 21
    }

    private fun isBlackjack(): Boolean {
        return totalScore == 21
    }

    companion object {
        const val MINIMUM_CARDS_COUNT = 2
    }
}
