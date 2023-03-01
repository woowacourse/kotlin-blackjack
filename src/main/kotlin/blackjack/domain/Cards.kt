package blackjack.domain

class Cards {
    private val _items: MutableList<Card> by lazy { mutableListOf() }
    val items: List<Card>
        get() = _items.toList()

    fun add(card: Card) {
        _items.add(card)
    }

    fun calculateTotalScore(): Int = items.map { it.number.toInt() }.fold(0) { total, number -> total + calculateEachScore(total, number) }

    private fun calculateEachScore(score: Int, number: Int): Int = when (number) {
        CardNumber.ace() -> calculateAceScore(score)
        CardNumber.jack(), CardNumber.queen(), CardNumber.king() -> JQK_SCORE
        else -> number
    }

    private fun calculateAceScore(score: Int): Int =
        if (score + ACE_MAX_SCORE > SCORE_LIMIT) ACE_MIN_SCORE else ACE_MAX_SCORE

    companion object {
        private const val ACE_MIN_SCORE = 1
        private const val ACE_MAX_SCORE = 11
        private const val SCORE_LIMIT = 21
        private const val JQK_SCORE = 10
    }
}
