package blackjack.model.domain.card

import blackjack.model.service.Blackjack.Companion.BUST_STANDARD

class Hand(private val _cards: MutableList<Card>) {
    val cards get() = _cards.deepCopy()

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

    fun isBust(): Boolean {
        return getSumNumber() > BUST_STANDARD
    }

    private fun MutableList<Card>.deepCopy(): List<Card> = map { it.copy() }
}
