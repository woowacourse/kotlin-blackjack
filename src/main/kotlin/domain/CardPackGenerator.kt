package domain

import model.Card
import model.Cards
import model.Rank
import model.Suit

class CardPackGenerator {
    private val rankValues = Rank.values()
    private val suitValues = Suit.values()

    fun createCards(): Cards {
        return Cards(
            buildList {
                rankValues.forEach { rank -> addAll(cardsBySuit(rank)) }
            },
        )
    }

    private fun cardsBySuit(rank: Rank) = suitValues.map { suit -> Card(rank, suit) }
}
