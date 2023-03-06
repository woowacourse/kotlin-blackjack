package model

abstract class Participant(val hand: Hand, val name: Name) {
    abstract fun isHit(): Boolean
    fun isBust(): Boolean {
        if (hand.sum() > BUST_POINT) return true
        return false
    }

    fun pick(cardPack: CardPack) {
        hand.add(cardPack.pop())
    }
    companion object {
        private const val BUST_POINT = 21
    }
}
