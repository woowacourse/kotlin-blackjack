package domain.score

import domain.card.Card

private const val BLACK_JACK_POINT = 21

data class Score(val cards: List<Card>) {
    val value = sumCardsPoint()
    val type = ScoreType.of(value, cards.size)

    private fun sumCardsPoint(): Int {
        var sum = cards.sumBy { it.point }
        cards.forEach {
            sum += decideSpecialPoint(sum, it.specialPoint)
        }
        return sum
    }

    private fun decideSpecialPoint(sum: Int, specialPoint: Int): Int {
        if (sum + specialPoint <= BLACK_JACK_POINT) {
            return specialPoint
        }
        return 0
    }

    fun isWin(other: Score): Boolean {
        if (this.type.isBlackJack() && !other.type.isBlackJack()) {
            return true
        }

        if (!this.type.isOver() && other.type.isOver()) {
            return true
        }

        if (this.type.isUnder() && other.type.isUnder()) {
            return this.value > other.value
        }

        return false
    }

    fun isLose(other: Score) = other.isWin(this)
}