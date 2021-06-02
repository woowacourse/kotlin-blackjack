package domain


class Cards(val cards: MutableList<TrumpCard>) {
    fun getTotalScore(): Int {
        val defaultScore = cards.map { it.getScore() }.sum()
        if (canPlusScore(defaultScore)) {
            return defaultScore + 10
        }
        return defaultScore
    }

    private fun hasAce(): Boolean {
        return cards.any { it.trumpCardNumber == TrumpCardNumber.ACE }
    }

    private fun canPlusScore(defaultScore: Int): Boolean {
        return hasAce() && defaultScore + 10 <= 21
    }

    fun dealCard(): TrumpCard {
        if (this.cards.isEmpty()) {
            throw EmptyCardException()
        }

        val last = this.cards.last()
        this.cards.removeLast()
        return last
    }
}
