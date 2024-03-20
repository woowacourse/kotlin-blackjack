package blackjack.model

class RandomShuffleCardDeck : ShuffleCardDeck {
    override fun <T> shuffle(deck: List<T>): List<T> {
        return deck.shuffled().toList()
    }
}
