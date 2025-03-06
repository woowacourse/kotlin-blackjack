package blackjack.domain

abstract class Participant {
    val hand = Hand(emptyList())

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun calculateScore(): Int = hand.calculateScore()

    fun isBust(): Boolean = hand.isBust()
}
