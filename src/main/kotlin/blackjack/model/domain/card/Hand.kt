package blackjack.model.domain.card

class Hand(private val _cards: MutableList<Card>) {
    private var _status: Status = Status.NEUTRAL

    val cards get() = _cards.deepCopy()
    val status get() = _status

    fun getSumNumber(): Int {
        var sum = cards.sumOf { it.cardNumber.number }

        if (_cards.any { it.isAce() } && sum + CardNumber.BONUS_SCORE <= BUST_STANDARD) {
            sum += CardNumber.BONUS_SCORE
        }

        return sum
    }

    fun append(card: List<Card>) {
        _cards.addAll(card)
    }

    fun isBust() {
        if (getSumNumber() > BUST_STANDARD) this._status = Status.BUST
    }

    fun isBlackJack() {
        if (getSumNumber() == BUST_STANDARD) this._status = Status.BLACKJACK
    }

    private fun MutableList<Card>.deepCopy(): List<Card> = map { it.copy() }

    companion object {
        const val BUST_STANDARD: Int = 21
    }
}
