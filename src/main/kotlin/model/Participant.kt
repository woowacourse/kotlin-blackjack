package model

abstract class Participant(val hand: Hand, val name: Name) {
    abstract fun isHit(): Boolean
    fun isBust(): Boolean = hand.sum() > BLACKJACK_POINT

    fun isBlackJack(): Boolean = hand.sum() == BLACKJACK_POINT

    fun pick(cardPack: CardPack) {
        hand.add(cardPack.pop())
    }

    companion object {
        private const val BLACKJACK_POINT = 21
    }
}
