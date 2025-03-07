package blackjack.domain.card

class Deck {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    init {
        _cards.addAll(generateDeck())
        require(cards.size == DECK_SIZE) { INVALID_DECK_SIZE_ERROR_MESSAGE }
    }

    fun draw(): Card {
        require(cards.isNotEmpty()) { NO_SUCH_ELEMENT_ERROR_MESSAGE }
        return _cards.removeFirst()
    }

    private fun generateDeck(): List<Card> = CardPattern.entries.flatMap(::createCard).shuffled()

    private fun createCard(cardPattern: CardPattern): List<Card> {
        return CardNumber.entries.map { cardNumber -> Card.create(cardNumber, cardPattern) }
    }

    companion object {
        private const val DECK_SIZE = 52
        private const val INVALID_DECK_SIZE_ERROR_MESSAGE = "덱은 52장의 카드로 구성되어야 합니다."
        private const val NO_SUCH_ELEMENT_ERROR_MESSAGE = "남은 카드가 없습니다."
    }
}
