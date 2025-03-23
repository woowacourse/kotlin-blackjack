package blackjack.domain

class Hand(
    cards: List<Card>,
    val money: Int,
) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card>
        get() = _cards

    val size = cards.size

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getTotalScore(): Int {
        val score = _cards.sumOf { it.getScore() }
        if (_cards.any { it.hasAce() && score + 10 <= 21 }) {
            return score + 10
        }
        return score
    }

    fun hasBlackjack(): Boolean = _cards.size == BLACKJACK_SIZE && getTotalScore() == BLACKJACK_SCORE && hasAce()

    fun hasBust(): Boolean = getTotalScore() > BLACKJACK_SCORE

    private fun hasAce(): Boolean = _cards.any { it.hasAce() }

    companion object {
        const val BLACKJACK_SIZE = 2
        const val BLACKJACK_SCORE = 21
    }
}
