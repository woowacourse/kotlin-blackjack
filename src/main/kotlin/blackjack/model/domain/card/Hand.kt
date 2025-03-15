package blackjack.model.domain.card

import blackjack.model.service.Blackjack.Companion.BUST_STANDARD

class Hand(private val _cards: MutableList<Card>) {
    private var _status: Status = Status.NEUTRAL

    val cards get() = _cards.deepCopy()
    val status get() = _status

    fun getSumNumber(): Int {
        var sum = cards.sumOf { it.cardNumber.number }

        if (haveAce(cards.map { it.cardNumber }) && sum + CardNumber.BONUS_SCORE <= BUST_STANDARD) {
            sum += CardNumber.BONUS_SCORE
        }

        return sum
    }

    fun append(card: List<Card>) {
        _cards.addAll(card)
    }

    private fun haveAce(cardNumbers: List<CardNumber>): Boolean {
        return CardNumber.Ace in cardNumbers
    }

    fun isBust() {
        if (getSumNumber() > BUST_STANDARD) this._status = Status.BUST
    }

    fun isBlackJack() {
        if (getSumNumber() == BUST_STANDARD) this._status = Status.BLACKJACK
    }

    private fun MutableList<Card>.deepCopy(): List<Card> = map { it.copy() }
}
