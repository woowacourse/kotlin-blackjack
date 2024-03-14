package blackjack.model.card.generator

import blackjack.model.card.Card

interface CardGenerator {
    fun draw(cardDeck: List<Card>): Card
}
