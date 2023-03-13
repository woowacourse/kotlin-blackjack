package blackjack.domain.card

class Deck private constructor(cards: List<Card>) {
    private val cards = cards.toMutableList()

    fun draw(): Card {
        if (cards.isEmpty()) {
            cards.addAll(DeckMaker.getDeck())
        }
        return cards.removeFirst()
    }

    companion object {
        fun create() = Deck(DeckMaker.getDeck())
    }
}
