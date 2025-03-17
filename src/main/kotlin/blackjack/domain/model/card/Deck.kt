package blackjack.domain.model.card

import blackjack.domain.generator.CardsGenerator

class Deck(
    private val cardGenerator: CardsGenerator,
) {
    private val cards: ArrayDeque<Card> = ArrayDeque(cardGenerator.createCards())

    fun refill() {
        cards += cardGenerator.createCards()
    }

    fun pop(number: Int): List<Card>? {
        if (cards.isEmpty() || cards.size < number) return null
        return List(number) { cards.removeFirst() }
    }
}
