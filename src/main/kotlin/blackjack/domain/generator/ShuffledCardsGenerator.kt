package blackjack.domain.generator

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Suit

class ShuffledCardsGenerator : CardsGenerator {
    override fun createCards(): List<Card> {
        val cards = Suit.entries.flatMap { suit -> CardNumber.entries.map { cardNumber -> Card(cardNumber, suit) } }
        return cards.shuffled()
    }
}
