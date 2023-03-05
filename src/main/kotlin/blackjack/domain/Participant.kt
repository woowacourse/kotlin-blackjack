package blackjack.domain

abstract class Participant(name: ParticipantName) {
    private val _name = name
    val name = _name.value
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

    private fun adjustAceValue(score: Int): Int = if (score > TARGET_SCORE) score - GAP_ACE else score

    fun canHit(): Boolean = this.getScore() < TARGET_SCORE

    fun isBust(): Boolean = getScore() > TARGET_SCORE

    fun hasInitialCards(): Boolean = _cards.size >= INIT_CARD_SIZE

    companion object {
        const val INIT_CARD_SIZE = 2
        const val TARGET_SCORE = 21
        private val GAP_ACE: Int = CardNumber.ACE.value - 1
    }
}
