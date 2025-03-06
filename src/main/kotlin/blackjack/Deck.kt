package blackjack

class Deck {
    private val cards = ArrayDeque(Card.CARD_INDEX_RANGE.shuffled().map { Card(it) })

    fun drawCard(): Card {
        return cards.removeLast()
    }
}
