package blackjack.domain

class PlayerHand {
    private val _cards: MutableList<Card> by lazy { mutableListOf() }
    val cards: List<Card>
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun calculateTotalScore(): Int = 0 // cards.map { it.number.value }.fold(0) { total, number -> total + calculateEachScore(total, number) }

    private fun calculateEachScore(score: Int, number: Int): Int = when (number) {
        // CardNumber.ace() -> calculateAceScore(score)
        // CardNumber.jack(), CardNumber.queen(), CardNumber.king() -> JQK_SCORE
        else -> number
    }

    private fun calculateAceScore(score: Int): Int =
        if (score + ACE_MAX_SCORE > GameResult.blackjackScore()) ACE_MIN_SCORE else ACE_MAX_SCORE

    companion object {
        private const val ACE_MIN_SCORE = 1
        private const val ACE_MAX_SCORE = 11
        private const val JQK_SCORE = 10
    }
}
