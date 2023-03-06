package blackjack.domain

import blackjack.const.MAX_SCORE_CONDITION

class CardBunch private constructor(cards: MutableList<Card>) {
    private val _cards: MutableList<Card> = cards
    val cards: List<Card> get() = _cards.toList()

    constructor(vararg cards: Card) : this(cards.toMutableList())

    fun addCard(card: Card) = _cards.add(card)


    fun getTotalScore(): Int {
        var result = 0
        val sortedCards = cards.sortedBy { it.cardNumber.value }.reversed()

        sortedCards.forEach { card ->
            result += if (card.cardNumber == CardNumber.ACE) checkAceValue(result) else card.cardNumber.value
        }
        return result
    }

    private fun checkAceValue(result: Int): Int {
        return when (result + BIG_ACE_SCORE > MAX_SCORE_CONDITION) {
            true -> SMALL_ACE_SCORE
            false -> BIG_ACE_SCORE
        }
    }

    fun isBurst(): Boolean = getTotalScore() > MAX_SCORE_CONDITION

    companion object {
        private const val BIG_ACE_SCORE = 11
        private const val SMALL_ACE_SCORE = 1
    }
}
