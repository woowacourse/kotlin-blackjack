package blackjack.model

interface ShuffleGenerator {
    fun shuffleGameDeck(cards: List<Card>? = null)
}
