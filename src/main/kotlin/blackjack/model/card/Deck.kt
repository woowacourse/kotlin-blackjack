package blackjack.model.card

class Deck private constructor(
    private val cards: ArrayDeque<Card>,
) {
    fun draw(): Card =
        cards.removeFirstOrNull() ?: run {
            createNewDeck()
            draw()
        }

    private fun createNewDeck() {
        if (cards.isNotEmpty()) cards.clear()
        cards.addAll(DeckFactory.createDeck())
    }

    companion object {
        const val INITIAL_HAND_OUT_CARD_COUNT = 2

        fun create(): Deck = Deck(DeckFactory.createDeck())
    }
}
