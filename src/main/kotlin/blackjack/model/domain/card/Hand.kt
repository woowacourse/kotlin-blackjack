package blackjack.model.domain.card

import blackjack.model.service.Blackjack.Companion.BUST_STANDARD

class Hand(val cards: MutableList<Card>) {
    fun getSumNumber(): Int {
        var sum = cards.sumOf { it.cardNumber.number }
        val haveAce: Boolean = CardNumber.Ace in cards.map { it.cardNumber }

        if (haveAce && sum + CardNumber.BONUS_SCORE < BUST_STANDARD) {
            sum += CardNumber.BONUS_SCORE
        }

        return sum
    }

    fun append(card: Card) {
        cards.add(card)
    }
}
