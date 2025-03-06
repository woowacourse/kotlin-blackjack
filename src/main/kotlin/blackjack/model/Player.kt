package blackjack.model

open class Player(
    open val name: String,
    cards: List<Card> = emptyList(),
) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    open val cards: List<Card> get() = _cards.map { it.copy() }

    open fun appendCard(card: Card) {
        _cards.add(card)
    }
}
