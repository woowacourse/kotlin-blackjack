package model

abstract class Human(open val hand: Hand) {

    fun getPointIncludingAce(): Point {
        return if (hand.cards.any { it.value.amount % 13 == 0 }) {
            decideAceValue()
        } else hand.getPoint()
    }

    private fun decideAceValue(): Point {
        val point = hand.getPoint().amount
        return if (point <= 11) Point(point + 10)
        else Point(point)
    }

    abstract fun hit(): Boolean
    abstract fun isPossible(): Boolean
}
