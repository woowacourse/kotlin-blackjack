package blackjack.model

abstract class Participant {
    val hand = Hand()

    fun draw(cardDeck: CardDeck) {
        val count = if (hand.cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        hand.addAll(cardDeck.draw(count))
    }

    companion object {
        private const val INITIAL_DRAW_COUNT = 2
        private const val DEFAULT_DRAW_COUNT = 1
    }
}
