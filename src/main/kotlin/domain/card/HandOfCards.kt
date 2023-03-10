package domain.card

import constant.BlackJackConstants.BLACK_JACK_NUMBER
import constant.BlackJackConstants.DEALER_STAND_CONDITION
import domain.card.CardNumber.ACE

class HandOfCards(cards: List<Card>) {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    init {
        require(cards.size == 2) { "[ERROR] 2장의 카드가 들어오지 않았습니다. 들어온 카드 수: ${cards.size}" }
        this._cards.addAll(cards)
    }

    constructor(card1: Card, card2: Card) : this(listOf(card1, card2))

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
