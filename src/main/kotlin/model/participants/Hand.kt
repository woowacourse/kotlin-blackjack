package model.participants

import model.card.Card
import model.card.Deck
import model.card.TrumpDeck
import model.result.Point

class Hand(private val deck: Deck = TrumpDeck) {
    private var _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards

    fun draw() {
        _cards.add(deck.pop())
    }

    val point: Point
        get() = calculatePoint()

    private fun calculatePoint(): Point {
        return _cards.sumOf { card ->
            card.valueType.amount
        }.run { Point(this) }
    }

    fun hasAce(): Boolean {
        return _cards.any { it.valueType.amount.rem(CARD_DIVIDER) == 0 }
    }

    companion object {
        const val CARD_DIVIDER = 13
    }
}
