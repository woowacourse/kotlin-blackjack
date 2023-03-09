package domain

import domain.card.Card
import domain.card.CardValue

class Cards(cards: List<Card>) {
    private val _value: MutableList<Card> = cards.toMutableList()
    val value: List<Card> get() = _value.toList()

    private val numbers: List<Int> get() = _value.map { it.value.number }

    val score: Score get() = Score.valueOfCards(numbers, hasAce())

    fun addCard(card: Card) {
        _value.add(card)
    }

    private fun hasAce() = (countAce() > 0)

    private fun countAce(): Int = _value.count { card ->
        card.value == CardValue.ACE
    }

    fun isBlackJack(): Boolean = isTwoCards() && isBlackJackScore()

    private fun isTwoCards(): Boolean = (_value.size == NUMBER_OF_BLACKJACK_CARDS)
    private fun isBlackJackScore(): Boolean = Score.valueOfCards(numbers, hasAce()) == Score.valueOf(BLACKJACK_SCORE)

    companion object {
        private const val NUMBER_OF_BLACKJACK_CARDS = 2
        private const val BLACKJACK_SCORE = 21
    }
}
