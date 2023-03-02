package blackjack.domain

object Deck {
    private var index: Int = 0
    private val cards: MutableList<Card> = CardNumber.values()
        .flatMap { number -> CardShape.values().map { number to it } }
        .map { Card(number = it.first, shape = it.second) }
        .shuffled()
        .toMutableList()

    private fun refillDeck() {
        cards.shuffle()
        index = 0
    }

    fun draw(): Card {
        if (index == cards.size) {
            refillDeck()
        }
        return cards[index++]
    }
}
