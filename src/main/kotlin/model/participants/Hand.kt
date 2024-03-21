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
        get() = calculatePoint()

    val optimalPoint: Point
        get() = calculateOptimalPoint()

    fun draw(card: Card) {
        _cards.add(card)
    }

    private fun calculatePoint(): Point {
        return _cards.sumOf { card ->
            card.valueType.amount
        }.run { Point(this) }
    }

    private fun hasAce(): Boolean {
        return _cards.any { it.valueType == ValueType.ACE }
    }

    fun isBlackjack(): Boolean {
        return calculateOptimalPoint() == Point(BLACK_JACK_POINT)
    }

    fun calculateOptimalPoint(): Point {
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
        const val BLACKJACK_CARD_COUNT = 2
    }
}
