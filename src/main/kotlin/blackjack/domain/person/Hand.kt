package blackjack.domain.person

import blackjack.domain.card.Card

class Hand {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(cards: List<Card>) {
        _cards.addAll(cards)
    }

    fun score(): Int {
        val total = cards.sumOf { it.number.value }

        if (total + 10 > 21) return total

        return total + 10
    }
}
