package blackjack.domain.model.card

import blackjack.domain.generator.CardsGenerator

class Deck(
    private val cardGenerator: CardsGenerator,
) {
    private val cards: ArrayDeque<Card> = ArrayDeque(cardGenerator.createCards())

    fun pop(): Card {
        if (cards.isEmpty()) {
            cards.addAll(cardGenerator.createCards())
        }
        return cards.removeFirst()
    }
}
