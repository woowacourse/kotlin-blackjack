package blackjack.model

abstract class Participant(firstCard : List<Card>) {
    val hand = Hand(firstCard)

    fun draw(cardDeck: CardDeck) {
        hand.add(cardDeck.draw())
    }

    companion object {
        private const val INITIAL_DRAW_COUNT = 2
        private const val DEFAULT_DRAW_COUNT = 1
    }
}
