package blackjack.model

class Dealer(
    cards: List<Card> = emptyList(),
) : Player("딜러") {
    private val _cards: MutableList<Card> = cards.toMutableList()
    override val cards: List<Card> get() = _cards.map { it.copy() }

    override fun appendCard(card: Card) {
        _cards.add(card)
    }
}
