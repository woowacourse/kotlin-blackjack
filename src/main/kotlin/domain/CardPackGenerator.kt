package domain

import model.Card
import model.CardPack
import model.Rank
import model.Suit

class CardPackGenerator {

    fun createCardPack(): CardPack = CardPack(createCards())

    private fun createCards() = Rank.values().flatMap { rank -> createCardsBySuit(rank) }

    private fun createCardsBySuit(rank: Rank) = Suit.values().map { suit -> Card(rank, suit) }
}
