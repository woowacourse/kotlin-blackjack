package blackjack.model

class GameDeck : DeckManager {
    private var deck: List<Card> = createGameDeck().shuffled()
    private var index: Int = CARD_DRAW_DEFAULT_INDEX

    override fun resetDeck(cards: List<Card>?) {
        deck = cards ?: createGameDeck().shuffled()
    }

    override fun resetDeckIndex(newIndex: Int?) {
        index = newIndex ?: CARD_DRAW_DEFAULT_INDEX
    }

    fun drawCard(): Card {
        if (index < deck.size) {
            return deck[index++]
        }
        resetDeck()
        resetDeckIndex()
        return deck[index++]
    }

    private fun createGameDeck(): List<Card> = Card.createDeck()

    companion object {
        private const val CARD_DRAW_DEFAULT_INDEX = 0
    }
}
