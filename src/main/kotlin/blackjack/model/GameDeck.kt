package blackjack.model

class GameDeck : DeckManager {
    private val deck: MutableList<Card> = Card.cards.shuffled().toMutableList()

    override fun resetDeck(cards: List<Card>?) {
        deck.clear()
        if (cards == null) {
            deck.addAll(Card.cards.shuffled())
        } else {
            deck.addAll(cards)
        }
    }

    override fun drawCard(position: Int): Card {
        if (position < deck.size) {
            return deck.removeAt(position)
        }
        resetDeck()
        return deck.removeAt(position)
    }

    companion object {
        const val CARD_DRAW_DEFAULT_INDEX = 0
    }
}
