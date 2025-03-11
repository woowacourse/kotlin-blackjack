package blackjack.model.card

class Deck private constructor(private val cards: ArrayDeque<Card>) {
    fun draw(): Card {
        return cards.removeFirstOrNull() ?: run {
            createNewDeck()
            draw()
        }
    }

    private fun createNewDeck() {
        if (cards.isNotEmpty()) cards.clear()
        cards.addAll(DeckFactory.createDeck())
    }

    companion object {
        const val INITIAL_HAND_OUT_CARD_COUNT = 2

        fun create(): Deck {
            return Deck(DeckFactory.createDeck())
        }
    }
}
