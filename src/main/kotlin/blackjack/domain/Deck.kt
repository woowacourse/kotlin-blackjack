package blackjack.domain

object Deck {
    private var index: Int = 0
    private val cards: MutableList<Card> = CardNumber.values()
        .flatMap { number -> CardShape.values().map { number to it } }
        .map { Card(number = it.first, shape = it.second) }
        .shuffled()
        .toMutableList()

    fun draw(): Card {
        if (isExhausted()) {
            refillDeck()
        }
        return cards[index++]
    }

    private fun isExhausted(): Boolean = index == cards.size

    private fun refillDeck() {
        cards.shuffle()
        index = 0
    }
}
