package blackjack.model

class CardDeck(private val cards: MutableList<Card>) {
    fun getCards(): List<Card> {
        return cards.toList()
    }

    fun pick(): Card = cards.removeLast()

    fun createStartHand(): Hand {
        return Hand(
            MutableList(INITIAL_DISTRIBUTE_COUNT) { pick() },
        )
    }

    companion object {
        private const val INITIAL_DISTRIBUTE_COUNT = 2
    }
}
