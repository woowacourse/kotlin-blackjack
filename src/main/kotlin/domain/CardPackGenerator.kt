package domain

import model.Card
import model.CardDeck
import model.Rank
import model.Suit

class CardPackGenerator {
    fun createCardDeck(): CardDeck {
        return CardDeck(
            Rank.values().flatMap { rank -> Suit.values().map { suit -> Card(rank, suit) } }
        )
    }
}
