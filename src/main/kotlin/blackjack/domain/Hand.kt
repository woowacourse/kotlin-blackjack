package blackjack.domain

class Hand(
    cards: List<Card>,
) {
    private val _cards: MutableList<Card> = cards.toMutableList()

    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
