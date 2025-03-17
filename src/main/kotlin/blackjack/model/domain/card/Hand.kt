package blackjack.model.domain.card

class Hand(private val _cards: MutableList<Card>) {
    val cards get() = _cards.deepCopy()

    fun getSumNumber(): Int {
        val sum = _cards.sumOf { it.cardNumber.number }
        return if (_cards.any { it.isAce() } && sum + CardNumber.BONUS_SCORE <= BUST_STANDARD) {
            sum + CardNumber.BONUS_SCORE
        } else {
            sum
        }
    }

    fun append(card: List<Card>) {
        _cards.addAll(card)
    }

    fun isBust(): Boolean {
        return getSumNumber() > BUST_STANDARD
    }

    fun isBlackJack(): Boolean {
        return getSumNumber() == BUST_STANDARD && _cards.size == INIT_HAND_SIZE
    }

    private fun MutableList<Card>.deepCopy(): List<Card> = map { it.copy() }

    companion object {
        const val BUST_STANDARD: Int = 21
        const val INIT_HAND_SIZE: Int = 2
    }
}
