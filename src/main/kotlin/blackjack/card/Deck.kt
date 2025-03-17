package blackjack.card

class Deck(cards: List<Card>) {
    private val deck: ArrayDeque<Card> = ArrayDeque(cards)

    fun draw(): Card {
        require(deck.isNotEmpty()) { "덱이 비어 있습니다" }
        return deck.removeFirst()
    }
}
