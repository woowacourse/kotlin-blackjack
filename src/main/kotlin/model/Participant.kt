package model

abstract class Participant(val name: Name, val cards: Cards) {
    abstract fun isHit(): Boolean
    fun isBust(): Boolean {
        if (cards.sum() > 21) return true
        return false
    }
}
