package domain.card

class Deck {
    private val cards = mutableListOf<Card>()

    init {
        initCards()
        cards.shuffle()
        checkDeckSize()
    }

    private fun initCards() {
        CardShape.values().forEach { shape ->
            addNumbers(shape)
        }
    }

    private fun addNumbers(shape: CardShape) {
        CardNumber.values().forEach { number ->
            cards.add(Card(shape, number))
        }
    }

    private fun checkDeckSize() {
        require(cards.size == DECK_SIZE) { ERROR_DECK_SIZE }
    }

    fun getCard() = cards.removeFirst()

    companion object {
        private const val DECK_SIZE = 52
        private const val ERROR_DECK_SIZE = "카드는 52개 생성되어야 합니다."
    }
}
