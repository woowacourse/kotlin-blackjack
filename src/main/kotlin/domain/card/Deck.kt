package domain.card

class Deck private constructor(cards: List<Card>) {
    private val _cards = cards.toMutableList()

    init {
        checkDeckSize()
    }

    private fun checkDeckSize() {
        require(_cards.size == DECK_SIZE) { ERROR_DECK_SIZE }
    }

    fun getCard() = _cards.removeFirst()

    companion object {
        private const val DECK_SIZE = 52
        private const val ERROR_DECK_SIZE = "덱은 52개의 카드로 구성되어야 합니다."

        private val CARDS = CardShape.values().flatMap { shape -> makeCardsOf(shape) }
        private fun makeCardsOf(shape: CardShape) =
            CardNumber.values().map { number -> Card(shape, number) }

        fun create() = Deck(CARDS.shuffled())
    }
}
