package blackjack.domain

abstract class Participant {
    val hand = Hand(emptyList())

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    abstract fun canDraw(): Boolean
}
