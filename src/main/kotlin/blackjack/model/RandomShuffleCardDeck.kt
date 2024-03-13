package blackjack.model

class RandomShuffleCardDeck : ShuffleCardDeck {
    override fun <T> shuffle(deck: MutableList<T>): MutableList<T> {
        return deck.shuffled().toMutableList()
    }
}
