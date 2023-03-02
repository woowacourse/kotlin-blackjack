package model

abstract class Participant(val cards: Cards) {
    abstract fun isHit(): Boolean
    fun isBurst(): Boolean {
        if (cards.sum() > 21) return true
        return false
    }
}
