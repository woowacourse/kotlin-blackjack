package blackjack.domain

class Player(
    private val name: String,
    val hand: Hand,
) {
    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun calculateScore(): Int = hand.calculateScore()
}
