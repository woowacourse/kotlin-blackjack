package blackjack.model

class GameInfo(
    val name: String,
    cards: Set<Card> = emptySet(),
) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun sumCardValues(): Int {
        var total = cards.sumOf { it.value }

        if (total <= 11 &&
            cards.contains(Card(Shape.HEART.title, "A", 1)) ||
            cards.contains(Card(Shape.DIAMOND.title, "A", 1)) ||
            cards.contains(Card(Shape.CLOVER.title, "A", 1)) ||
            cards.contains(Card(Shape.SPADE.title, "A", 1))
        ) {
            total += 10
        }

        return total
    }
}
