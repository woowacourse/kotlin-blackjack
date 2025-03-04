package blackjack.model

import blackjack.model.CardRank.Companion.score

class Hand {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun score(): Int = cards.sumOf { it.rank.score() }
}
