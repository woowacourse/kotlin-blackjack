package blackjack.domain

class Dealer(
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
