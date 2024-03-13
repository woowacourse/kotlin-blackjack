package blackjack.model

interface ShuffleCardDeck {
    fun <T> shuffle(deck: MutableList<T>): MutableList<T>
}
