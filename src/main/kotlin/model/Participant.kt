package model

abstract class Participant(val cards: Cards, val name: Name) {
    abstract fun isHit(): Boolean
    fun isBust(): Boolean {
        if (cards.sum() > 21) return true
        return false
    }
}
