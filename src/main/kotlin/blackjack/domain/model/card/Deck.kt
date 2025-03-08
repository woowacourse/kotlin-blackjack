package blackjack.domain.model.card

import blackjack.domain.generator.CardsGenerator

class Deck(
    cardGenerator: CardsGenerator,
) {
    private val cards: ArrayDeque<Card> = ArrayDeque(cardGenerator.createCards())

    fun pop(): Card {
        return cards.removeFirst()
    }
}
