package blackjack.domain.model

import blackjack.domain.generator.CardGenerator

class Deck(
    cardGenerator: CardGenerator,
) {
    private val cards: ArrayDeque<Card> = ArrayDeque(cardGenerator.create())

    fun pop(): Card {
        return cards.removeFirst()
    }
}
