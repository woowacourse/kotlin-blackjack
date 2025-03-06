package blackjack.model

abstract class Participant(
    private val cardDeck: CardDeck,
) {
    val hand = Hand()

    init {
        draw(INITIAL_DRAW_COUNT)
    }

    fun draw(count: Int = DEFAULT_DRAW_COUNT) {
        hand.addAll(cardDeck.draw(count))
    }

    companion object {
        private const val INITIAL_DRAW_COUNT = 2
        private const val DEFAULT_DRAW_COUNT = 1
    }
}
