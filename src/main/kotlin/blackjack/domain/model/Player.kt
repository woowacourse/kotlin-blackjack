package blackjack.domain.model

open class Player(val name: String, initCards: List<Card> = listOf()) {
    private val cards: MutableList<Card> = initCards.toMutableList()

    init {
        require(name.isNotBlank()) { ERROR_MESSAGE_BLANK_PLAYER_NAME }
    }

    fun showCards(count: Int = cards.size): List<Card> {
        return this.cards.take(count).map { it.copy() }
    }

    fun accept(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun getScore(): Int {
        val score = this.cards.sumOf { it.rank.score }
        return score + getBonusScore(totalScore = score)
    }

    private fun getBonusScore(totalScore: Int): Int {
        if (totalScore <= MAX_BONUS_SCORE && hasAce()) return BONUS_SCORE
        return 0
    }

    private fun hasAce(): Boolean = this.cards.any { it.rank == Rank.ACE }

    fun isBust(): Boolean {
        return getScore() > BUST_THRESHOLD
    }

    companion object {
        private const val BUST_THRESHOLD = 21
        private const val MAX_BONUS_SCORE = 11
        private const val BONUS_SCORE = 10
        private const val ERROR_MESSAGE_BLANK_PLAYER_NAME = "참가자의 이름은 공백일 수 없습니다."
    }
}
