package blackjack.domain.model.card

class Hand(cards: MutableList<Card>) {
    private val _cards: MutableList<Card> = cards
    val cards: List<Card>
        get() = _cards.deepCopy()

    fun getSumNumber(): Int {
        var sum = cards.sumOf { it.cardNumber.number }
        val haveAce: Boolean = Denomination.Ace in cards.map { it.cardNumber }

        if (haveAce && sum + Denomination.BONUS_SCORE <= BUST_STANDARD) {
            sum += Denomination.BONUS_SCORE
        }

        return sum
    }

    fun isBust(): Boolean {
        return getSumNumber() > BUST_STANDARD
    }

    fun isBlackjack(): Boolean {
        return _cards.size == 2 && getSumNumber() == BUST_STANDARD
    }

    operator fun plus(cards: List<Card>) {
        _cards.addAll(cards)
    }

    companion object {
        const val BUST_STANDARD: Int = 21
    }
}

private fun MutableList<Card>.deepCopy(): List<Card> = map { it.copy() }.toList()
