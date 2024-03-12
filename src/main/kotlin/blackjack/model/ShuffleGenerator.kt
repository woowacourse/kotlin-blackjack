package blackjack.model

interface ShuffleGenerator {
    fun shuffleGameDeck()

    fun shuffleGameDeck(cards: List<Card>)
}
