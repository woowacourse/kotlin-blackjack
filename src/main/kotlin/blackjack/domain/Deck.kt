package blackjack.domain

class Deck(
    cards: List<Card>,
) {
    val cards: ArrayDeque<Card> = ArrayDeque(cards)

    fun pick(): Card? = cards.removeLastOrNull()
}
