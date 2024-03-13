package blackjack.model

interface ShuffleCardDeck {
    fun <T> shuffle(deck: List<T>): List<T>
}
