package blackjack.domain.card

class Deck {

    private val cards: MutableList<Card> = (1..INIT_CARD_SET_SIZE).flatMap {
        createCardSet()
    }
        .shuffled()
        .toMutableList()

    private fun createCardSet(): List<Card> = CardNumber.values()
        .flatMap { cardNumber ->
            CardShape.values().map { Card(number = cardNumber, shape = it) }
        }

    fun draw(): Card? = cards.removeLastOrNull()

    fun isNotExhausted() = cards.isNotEmpty()

    companion object {
        private const val INIT_CARD_SET_SIZE = 6
    }
}
