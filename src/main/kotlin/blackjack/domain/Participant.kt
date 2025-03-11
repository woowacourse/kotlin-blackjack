package blackjack.domain

abstract class Participant {
    val hand: Hand = Hand()

    val totalSum: Int
        get() = hand.getCardSum()

    abstract val hitThreshold: Int

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun isBust(): Boolean {
        return totalSum > BUST_THRESHOLD
    }

    fun canHit(): Boolean {
        return totalSum < hitThreshold
    }

    companion object {
        const val BUST_THRESHOLD = 21
    }
}
