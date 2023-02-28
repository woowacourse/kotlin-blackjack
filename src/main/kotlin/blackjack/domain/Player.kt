package blackjack.domain

class Player(val name: String) {

    private val _cards = mutableSetOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun receive(card: Card) {
        _cards.add(card)
    }

    fun getScore(): Int {
        var score = _cards.sumOf { it.value }

        val aceCount = _cards.count { it.isAce() }
        repeat(aceCount) { score = updateScore(score) }
        return score
    }

    private fun updateScore(score: Int): Int = if (score > TARGET_SCORE) score - GAP_ACE else score

    companion object {
        private const val TARGET_SCORE = 21
        private val GAP_ACE: Int = CardNumber.ACE.value - 1
    }
}
