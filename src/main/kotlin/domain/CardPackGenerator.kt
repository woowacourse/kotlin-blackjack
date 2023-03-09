package domain

import model.cards.Card
import model.cards.CardPack
import model.cards.Rank
import model.cards.Suit

class CardPackGenerator {

    fun createCardPack(): CardPack = CardPack(createCards())

    private fun createCards() = Rank.values().flatMap { rank -> createCardsBySuit(rank) }

    private fun createCardsBySuit(rank: Rank) = Suit.values().map { suit -> Card(rank, suit) }
}
