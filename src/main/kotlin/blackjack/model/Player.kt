package blackjack.model

class Player(
    val name: String,
    cards: List<Card> = emptyList(),
) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    val cards: List<Card> get() = _cards.map { it.copy() }

    fun appendCard(card: Card) {
        _cards.add(card)
    }
}
