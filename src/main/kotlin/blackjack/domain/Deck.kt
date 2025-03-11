package blackjack.domain

class Deck(shuffledCards: List<Card>) {
    private val deck : ArrayDeque<Card> = ArrayDeque(shuffledCards)

    fun draw(): Card {
        require(deck.isNotEmpty()) { "덱이 비어 있습니다" }
        return deck.removeFirst()
    }

    fun getSize() = deck.size

    fun contains(card: Card) = deck.contains(card)
}
