package model

import model.card.Card

class Hand() {
    private var _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.toList()

    fun draw(newCard: Card) {
        _cards.add(newCard)
    }

    fun isNotBusted(): Boolean = getPoint().isLessOrEqualTo(BLACKJACK_CONDITION)

    fun getPoint(): Point {
        return if (hasAce() && getPointSum().isLessOrEqualTo(ACE_CONDITION)) {
            getPointSum().plus(BONUS_POINT)
        } else {
            getPointSum()
        }
    }

    private fun getPointSum(): Point {
        return _cards.sumOf { card ->
            card.denomination.point
        }.run { Point(this) }
    }

    private fun hasAce(): Boolean {
        return cards.any { card ->
            card.isAce()
        }
    }

    companion object {
        private const val ACE_CONDITION = 11
        private const val BONUS_POINT = 10
        private const val BLACKJACK_CONDITION = 21
    }
}
