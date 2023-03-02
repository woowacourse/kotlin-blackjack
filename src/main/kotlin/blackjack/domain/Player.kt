package blackjack.domain

class Player(val name: String) {
    val hand = PlayerHand()

    fun addCard(card: Card) {
        hand.add(card)
    }

    fun isBust(): Boolean = hand.calculateTotalScore() > PlayerHand.blackjackScore()

    fun getTotalScore(): Int = hand.calculateTotalScore()
}
