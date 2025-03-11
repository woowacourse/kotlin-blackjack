package blackjack.domain.generator

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.Card.Companion.standardCards

class ShuffledCardsGenerator : CardsGenerator {
    override fun createCards(): List<Card> {
        val cards: List<Card> = standardCards.values.toList()
        return cards.shuffled()
    }
}
