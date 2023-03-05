package domain.card

class Deck(private val cards: MutableList<Card>) {
    init {
        checkDeckSize()
    }

    private fun checkDeckSize() {
        require(cards.size == DECK_SIZE) { ERROR_DECK_SIZE }
    }

    fun getCard() = cards.removeFirst()

    fun shuffleDeck() {
        cards.shuffle()
    }

    companion object {
        private const val DECK_SIZE = 52
        private const val ERROR_DECK_SIZE = "덱은 52개의 카드로 구성되어야 합니다."
    }
}
