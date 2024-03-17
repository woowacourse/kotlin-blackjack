package blackjack.model

class GameDeck : DeckManager {
    private var deck: List<Card> = Card.cards.shuffled()
    private var index: Int = CARD_DRAW_DEFAULT_INDEX

    override fun resetDeck(cards: List<Card>?) {
        deck = cards ?: Card.cards.shuffled()
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

    companion object {
        private const val CARD_DRAW_DEFAULT_INDEX = 0
    }
}
