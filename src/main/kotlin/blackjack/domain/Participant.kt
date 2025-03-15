package blackjack.domain

abstract class Participant(val name: String) {
    val hand: Hand = Hand(emptyList())

    fun draw(deck: Deck) {
        val drawnCard = deck.draw()
        hand.addCard(drawnCard)
    }
}
