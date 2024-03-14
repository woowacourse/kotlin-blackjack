package blackjack.model

interface DeckManager {
    fun resetDeck(cards: List<Card>? = null)
}
