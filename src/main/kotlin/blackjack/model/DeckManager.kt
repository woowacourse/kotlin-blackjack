package blackjack.model

interface DeckManager {
    fun resetDeck(cards: List<Card>? = null)

    fun drawCard(position: Int): Card
}
