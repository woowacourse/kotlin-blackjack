package domain

abstract class Participant(val name: String, val hand: Hand = Hand()) {
    fun draw(card: Card) {
        hand.addCard(card)
    }

    fun score(): Int {
        return hand.getScore()
    }

    abstract fun isHitStatus(): Boolean
}