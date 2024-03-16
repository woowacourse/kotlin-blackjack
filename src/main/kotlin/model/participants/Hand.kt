package model.participants

import model.card.Card
import model.card.ValueType
import model.result.Point
import model.result.Point.Companion.compareTo

class Hand {
    private var _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards

    val point: Point
        get() = getPoint()

    val optimalPoint: Point
        get() = getOptimalPoint()

    fun draw(card: Card) {
        _cards.add(card)
    }

    private fun getPoint(): Point {
        return _cards.sumOf { card ->
            card.valueType.amount
        }.run { Point(this) }
    }

    private fun hasAce(): Boolean {
        return _cards.any { it.valueType == ValueType.ACE }
    }

    fun isBlackjack(): Boolean {
        return getOptimalPoint() == Point(BLACK_JACK_POINT)
    }

    fun getOptimalPoint(): Point {
        return if (hasAce()) {
            getPointWithAce()
        } else {
            point
        }
    }

    private fun getPointWithAce(): Point {
        val totalPoint = point + Card.ACE_ADDITIONAL_POINT
        return if (totalPoint <= BLACK_JACK_POINT) totalPoint else point
    }

    companion object {
        const val BLACK_JACK_POINT = 21
    }
}
