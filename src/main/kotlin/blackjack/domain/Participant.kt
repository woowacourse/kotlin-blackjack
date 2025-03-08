package blackjack.domain

abstract class Participant {
    val hand = Hand()

    fun drawCard(card: Card) {
        hand.addCard(card)
    }

    fun calculateScore(): Int = hand.calculateScore()

    fun isBust(): Boolean = hand.isBust()

    abstract fun canHit(): Boolean
}
