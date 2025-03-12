package blackjack.model.domain.card

import blackjack.model.service.Blackjack.Companion.BUST_STANDARD

class Hand(val cards: MutableList<Card>) {
    fun getSumNumber(): Int {
        var sum = cards.sumOf { it.cardNumber.number }

        if (haveAce(cards.map { it.cardNumber }) && sum + CardNumber.BONUS_SCORE <= BUST_STANDARD) {
            sum += CardNumber.BONUS_SCORE
        }

        return sum
    }

    fun append(card: List<Card>) {
        cards.addAll(card)
    }

    private fun haveAce(cardNumbers: List<CardNumber>): Boolean {
        return CardNumber.Ace in cardNumbers
    }
}
