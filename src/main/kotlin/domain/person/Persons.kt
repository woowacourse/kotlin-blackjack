package domain.person

import domain.card.Deck
import domain.card.HandOfCards

data class Persons(val dealer: Dealer, val players: List<Player>) {
    companion object {
        fun getPersons(names: List<String>, deck: Deck): Persons {
            val players = names.map { Player(it, HandOfCards(deck.getCard(), deck.getCard())) }
            val dealer = Dealer(HandOfCards(deck.getCard(), deck.getCard()))

            return Persons(dealer, players)
        }
    }
}
