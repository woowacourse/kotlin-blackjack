package blackjack.model

interface ShuffleGenerator {
    fun resetUserDeck(cards: List<Card>? = null)
}
