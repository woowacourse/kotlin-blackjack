package blackjack.model

class GameDeck : DeckManager {
    private var deck: List<Card> = createGameDeck().shuffled()
    private var index: Int = CARD_DRAW_DEFAULT_INDEX

    override fun resetDeck(cards: List<Card>?) {
        deck = emptyList()
        index = CARD_DRAW_DEFAULT_INDEX
        cards?.let { deck = it } ?: run { deck = createGameDeck().shuffled() }
    }

    fun drawCard(): Card {
        if (index < deck.size) {
            return deck[index++]
        }
        resetDeck()
        return deck[index++]
    }

    private fun createGameDeck(): List<Card> = Card.createDeck()

    companion object {
        private const val CARD_DRAW_DEFAULT_INDEX = 0
    }
}
