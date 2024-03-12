package blackjack.model.card.generator

import blackjack.model.card.Card

class RandomCardGenerator(private val cardDeck: List<Card>) : CardGenerator {
    private var cardIndex = 0

    override fun draw(): Card {
        if (cardIndex == cardDeck.size) {
            cardIndex = 0
        }
        return cardDeck[cardIndex++]
    }
}
