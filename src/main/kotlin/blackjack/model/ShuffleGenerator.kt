package blackjack.model

interface ShuffleGenerator {
    fun resetCurrentDeck(cards: List<Card>? = null)
}
