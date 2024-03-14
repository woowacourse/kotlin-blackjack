package blackjack.model

class GameInfo(
    val name: String,
    val moneyAmount: Money = Money(0),
    cards: Set<Card> = emptySet(),
) {
    private val _cards: MutableSet<Card> = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards

    val sumOfCards: Int
        get() = cards.sumOf { it.value }

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
