package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.TrumpCard

class Deck(
    private val shuffler: Shuffler,
) {
    private var cards: List<Card> = emptyList()

    init {
        refillDeck()
    }

    fun take(): Card {
        val card: Card =
            cards.firstOrNull() ?: run {
                refillDeck()
                cards.first()
            }
        cards = cards.minus(card)
        return card
    }

    private fun refillDeck() {
        cards = shuffler.shuffle(TrumpCard.getNewCardPack())
    }
}
