package blackjack.domain.card

class Deck(private val _cards: MutableList<Card> = mutableListOf()) {
    val cards: List<Card>
        get() = _cards.toList()

    init {
        if (_cards.isEmpty()) _cards.addAll(generateDeck())
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
        private const val NO_SUCH_ELEMENT_ERROR_MESSAGE = "남은 카드가 없습니다."
    }
}
