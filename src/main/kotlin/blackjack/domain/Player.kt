package blackjack.domain

class Player(
    name: String,
) : Person(name) {
    override fun draw(
        deck: Deck,
        amount: Int,
    ) {
        val drawnCards = deck.draw(amount)
        hand.addCard(drawnCards)
    }
}
