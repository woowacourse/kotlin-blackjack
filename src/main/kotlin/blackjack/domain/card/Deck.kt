package blackjack.domain.card

class Deck(initCards: List<Card> = CACHED_CARDS) {
    private val _cards: MutableList<Card> = initCards.toMutableList()
    val cards: List<Card> get() = _cards

    init {
        validateDeckSize()
    }

    fun draw(): Card {
        if (cards.isEmpty()) regenerateDeck()
        return _cards.removeFirst()
    }

    private fun regenerateDeck() {
        _cards.addAll(CACHED_CARDS)
        validateDeckSize()
    }

    private fun validateDeckSize() = require(cards.size == DECK_SIZE) { INVALID_DECK_SIZE_ERROR_MESSAGE.format(cards.size) }

    companion object {
        private const val DECK_SIZE = 52
        private const val INVALID_DECK_SIZE_ERROR_MESSAGE = "덱은 52장의 카드로 구성되어야 합니다. (%s 장)"

        private val CACHED_CARDS: List<Card> = CardPattern.entries.flatMap(::createCard)
            get() = field.shuffled()

        private fun createCard(cardPattern: CardPattern): List<Card> = CardNumber.entries.map { Card.create(it, cardPattern) }
    }
}
