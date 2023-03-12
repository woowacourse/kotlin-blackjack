package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class CardBunch(private val _cards: MutableList<Card>) {
    val cards: List<Card> get() = _cards.toList()

    constructor(vararg cards: Card) : this(cards.toMutableList())

    fun addCard(card: Card): Boolean = _cards.add(card)

    fun getSumOfCards(): Int {
        val sum = _cards.sumOf { it.cardNumber.value }
        return if (containAce()) decideAddTen(sum) else sum
    }

    fun size(): Int = _cards.size

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
