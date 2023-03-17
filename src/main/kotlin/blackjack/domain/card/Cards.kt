package blackjack.domain.card

import blackjack.domain.player.Player
import blackjack.domain.player.Player.Companion.MAX_SUM_NUMBER

class Cards(cards: List<Card> = listOf()) {

    constructor(vararg cards: Pair<CardNumber, CardShape>) : this(cards.map { Card.from(it.first, it.second) }.toList())

    private val _cards = cards.toMutableList()

    val values: List<Card>
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun isBlackjack() =
        (_cards.size == Player.CARD_SETTING_COUNT) && (sum() == MAX_SUM_NUMBER)

    fun sum(): Int {
        val sum = _cards.sumOf { it.number.value }
        if (containsCardNumber(CardNumber.ONE) && sum + ACE_BONUS <= MAX_SUM_NUMBER) return sum + ACE_BONUS
        return sum
    }

    private fun containsCardNumber(cardNumber: CardNumber): Boolean =
        _cards.any { it.number == cardNumber }

    companion object {
        private const val ACE_BONUS = 10
    }
}
