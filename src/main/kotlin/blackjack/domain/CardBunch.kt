package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class CardBunch private constructor(cards: MutableList<Card>) {
    private val _cards: MutableList<Card> = cards
    val cards: List<Card> get() = _cards.toList()

    constructor(vararg cards: Card) : this(cards.toMutableList())

    fun addCard(card: Card) = _cards.add(card)

    fun getSumOfCards(): Int {
        var result = 0
        cards.forEach { card ->
            result += card.cardNumber.value
        }
        if (containAce()) {
            result += checkAceValue(result)
        }
        return result
    }

    private fun containAce(): Boolean {
        _cards.forEach { card ->
            if (card.cardNumber == CardNumber.ACE) return true
        }
        return false
    }

    private fun checkAceValue(result: Int): Int {
        return when (result + TEN > MAX_SCORE_CONDITION) {
            true -> ZERO
            false -> TEN
        }
    }

    fun isBurst(): Boolean = getSumOfCards() > MAX_SCORE_CONDITION

    companion object {
        private const val ZERO = 0
        private const val TEN = 10
    }
}
