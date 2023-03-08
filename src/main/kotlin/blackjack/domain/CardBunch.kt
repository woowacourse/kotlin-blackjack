package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class CardBunch private constructor(cards: MutableList<Card>) {
    private val _cards: MutableList<Card> = cards
    val cards: List<Card> get() = _cards.toList()

    constructor(vararg cards: Card) : this(cards.toMutableList())

    fun addCard(card: Card): Boolean = _cards.add(card)

    fun getSumOfCards(): Int {
        var sum = cards.sumOf { it.cardNumber.value }
        if (containAce()) {
            sum = decideAddTen(sum)
        }
        return sum
    }

    private fun containAce(): Boolean {
        return _cards.any { card -> card.cardNumber == CardNumber.ACE }
    }

    private fun decideAddTen(sum: Int): Int {
        return when (sum + TEN > MAX_SCORE_CONDITION) {
            true -> sum
            false -> sum + TEN
        }
    }

    fun isBurst(): Boolean = getSumOfCards() > MAX_SCORE_CONDITION

    companion object {
        private const val TEN = 10
    }
}
