package blackjack.model

class GameInfo(
    val name: String,
    cards: Set<Card> = emptySet(),
) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards

    val total: Int
        get() = cards.sumOf { it.value }

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
