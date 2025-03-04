package blackjack.domain

data class Player(
    val name: String,
    val role: Role,
) {
    val hand: Hand = Hand()

    fun draw(
        deck: Deck,
        amount: Int,
    ) {
        val drawnCards = deck.draw(amount)
        hand.addCard(drawnCards)
    }
}
