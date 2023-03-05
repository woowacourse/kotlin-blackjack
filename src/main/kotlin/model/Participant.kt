package model

abstract class Participant(val cards: Cards, val name: Name) {
    abstract fun isHit(): Boolean
    fun isBust(): Boolean {
        if (cards.sum() > BUST_POINT) return true
        return false
    }

    fun pick(cardPack: Cards) {
        cards.add(cardPack.pop())
    }
    companion object {
        private const val BUST_POINT = 21
    }
}
