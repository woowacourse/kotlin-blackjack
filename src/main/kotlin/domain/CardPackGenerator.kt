package domain

import model.Card
import model.Cards
import model.Rank
import model.Suit

class CardPackGenerator {
    fun createCards(): Cards {
        return Cards(
            Rank.values().flatMap { rank -> Suit.values().map { suit -> Card(rank, suit) } }
        )
    }
}
