package blackjack.model

abstract class Participant {
    private val hand = Hand()

    fun draw(cardDeck: CardDeck) {
        val count = if (hand.cards.isEmpty()) INITIAL_DRAW_COUNT else DEFAULT_DRAW_COUNT
        hand.addAll(cardDeck.draw(count))
    }

    fun score(): Int = hand.score()

    fun cards(): List<Card> = hand.cards

    fun isBust(): Boolean = hand.isBust()

    companion object {
        private const val INITIAL_DRAW_COUNT = 2
        private const val DEFAULT_DRAW_COUNT = 1
    }
}
