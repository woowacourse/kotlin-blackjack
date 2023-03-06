package domain.card

class Deck {
    private val cards = mutableListOf<Card>()

    init {
        cards.addAll(initCards())
        cards.shuffle()
        checkDeckSize()
    }

    private fun initCards(): List<Card> {
        return CardShape.values().flatMap { shape ->
            initNumber(shape)
        }
    }

    private fun initNumber(shape: CardShape): List<Card> {
        return CardNumber.values().map { number ->
            Card(shape, number)
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
