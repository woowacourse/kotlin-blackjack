package model.participants

import model.card.Card
import model.card.Deck
import model.card.ValueType
import model.result.Point

class Hand {
    private var _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards

    fun draw(card: Card) {
        _cards.add(card)
    }

    val point: Point
        get() = calculatePoint()

    private fun calculatePoint(): Point {
        return _cards.sumOf { card ->
            card.valueType.amount
        }.run { Point(this) }
    }

    fun hasAce(): Boolean {
        return _cards.any { it.valueType == ValueType.ACE }
    }
}
