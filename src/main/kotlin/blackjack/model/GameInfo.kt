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
        var total = cards.sumOf { it.cardRank.value }

        if (total <= 11 && cards.any { it.cardRank == CardRank.ACE }) {
            total += 10
        }

        return total
    }
}
