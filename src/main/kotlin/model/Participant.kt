package model

abstract class Participant(private val hand: Hand) {
    abstract fun performTurn(cardDistributor: CardDistributor): Boolean

    abstract fun decideToHit(): Boolean

    fun getScore(): Int = hand.getScore()

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun getHand(): Hand = hand
}
