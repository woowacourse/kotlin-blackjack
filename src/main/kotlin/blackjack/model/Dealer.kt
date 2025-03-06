package blackjack.model

class Dealer(
    override val name: String = "딜러",
    cards: List<Card> = emptyList(),
) : Player(name) {
    private val _cards: MutableList<Card> = cards.toMutableList()
    override val cards: List<Card> get() = _cards.map { it.copy() }

    override fun appendCard(card: Card) {
        _cards.add(card)
    }
}
