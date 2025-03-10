package blackjack.domain.model

class Hands(private val _cards: List<Card>) {
    constructor(vararg card: Card) : this(card.toList())

    val cards get() = _cards.map { it.copy() }

    fun extractCards(count: Int): List<Card> = cards.take(count)

    fun isBust(): Boolean = getScore() > BUST_THRESHOLD

    fun nextHand(card: Card) = Hands(cards + card)

    fun getScore(): Int {
        val score = cards.sumOf { it.rank.score }
        return score + getBonusScore(totalScore = score)
    }

    private fun getBonusScore(totalScore: Int): Int {
        if (totalScore <= MAX_BONUS_SCORE && hasAce()) return BONUS_SCORE
        return 0
    }

    private fun hasAce(): Boolean = this.cards.any { it.rank == Rank.ACE }

    fun isStartCardCount(): Boolean = cards.count() == START_CARD_COUNT

    companion object {
        const val START_CARD_COUNT = 2
        private const val MAX_BONUS_SCORE = 11
        private const val BONUS_SCORE = 10
        private const val BUST_THRESHOLD = 21
    }
}
