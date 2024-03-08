package blackjack.model

class CardDeck(cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card>
        get() = _cards.toList()

    fun pick() = _cards.removeLast()

    fun initialDistribute(): Hand {
        return Hand(List(INITIAL_DISTRIBUTE_COUNT) { pick() })
    }

    companion object {
        private const val INITIAL_DISTRIBUTE_COUNT = 2
    }
}
