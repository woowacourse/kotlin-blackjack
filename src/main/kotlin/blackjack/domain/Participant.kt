package blackjack.domain

abstract class Participant(val name: String) {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun receive(card: Card) {
        _cards.add(card)
    }

    fun getScore(): Int {
        var score = _cards.sumOf { it.value }

        val aceCount = _cards.count { it.isAce() }
        repeat(aceCount) { score = adjustAceValue(score) }
        return score
    }

    fun isBust(): Boolean = getScore() > TARGET_SCORE

    private fun adjustAceValue(score: Int): Int = if (score > TARGET_SCORE) score - GAP_ACE else score

    companion object {
        private const val TARGET_SCORE = 21
        private val GAP_ACE: Int = CardNumber.ACE.value - 1
    }
}
