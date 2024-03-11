package blackjack.model

interface ShuffleGenerator {
    fun shuffleGameDeck(): List<Card>

    fun shuffleGameDeck(cards: List<Card>): List<Card>
}
