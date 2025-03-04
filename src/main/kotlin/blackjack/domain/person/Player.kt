package blackjack.domain.person

import blackjack.domain.card.Deck

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
