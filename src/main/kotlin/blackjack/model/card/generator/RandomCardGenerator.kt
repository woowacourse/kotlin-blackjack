package blackjack.model.card.generator

import blackjack.model.card.Card

object RandomCardGenerator : CardGenerator {
    private var cardIndex = 0

    override fun draw(cardDeck: List<Card>): Card {
        if (cardIndex == cardDeck.size) {
            cardIndex = 0
        }
        return cardDeck[cardIndex++]
    }
}
