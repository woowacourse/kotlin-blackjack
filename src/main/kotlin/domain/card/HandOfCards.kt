package domain.card

import constant.BlackJackConstants.BLACK_JACK_NUMBER
import constant.BlackJackConstants.DEALER_STAND_CONDITION
import domain.card.CardNumber.ACE

class HandOfCards() {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()
    val size: Int get() = cards.size

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getTotalCardSum(): Int {
        val sum = cards.sumOf { it.number.value }
        return when {
            sum + 10 > BLACK_JACK_NUMBER -> sum
            cards.any { it.number == ACE } -> sum + 10
            else -> sum
        }
    }

    fun showFirstCard(): List<Card> = cards.subList(0, 1)

    fun isBlackJack() = getTotalCardSum() == BLACK_JACK_NUMBER
    fun isDealerStay() = getTotalCardSum() > DEALER_STAND_CONDITION
    fun isBust() = getTotalCardSum() > BLACK_JACK_NUMBER
}
