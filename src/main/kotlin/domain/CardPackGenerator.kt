package domain

import model.Card
import model.Cards
import model.Rank
import model.Suit

class CardPackGenerator {

    fun createCardPack(): Cards {
        return Cards(createCards())
    }

    private fun createCards() = buildList {
        Rank.values().forEach { rank -> addAll(createCardsBySuit(rank)) }
    }

    private fun createCardsBySuit(rank: Rank) = Suit.values().map { suit -> Card(rank, suit) }
}
