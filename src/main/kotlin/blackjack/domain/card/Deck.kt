package blackjack.domain.card

class Deck private constructor(initialCards: List<Card>) {
    private val _cards: MutableList<Card> = initialCards.toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    fun draw(): Card {
        require(cards.isNotEmpty()) { NO_SUCH_ELEMENT_ERROR_MESSAGE }
        return _cards.removeFirst()
    }

    companion object {
        private const val NO_SUCH_ELEMENT_ERROR_MESSAGE = "남은 카드가 없습니다."

        fun createDefaultDeck(): Deck {
            val cards = CardFactory.create()
            return Deck(cards.shuffled())
        }

        fun createCustomDeck(cards: List<Card>): Deck {
            return Deck(cards)
        }
    }
}
