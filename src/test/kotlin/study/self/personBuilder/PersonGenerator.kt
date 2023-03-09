package study.self.personBuilder

import domain.card.Deck
import domain.person.Dealer
import domain.person.Persons
import domain.person.Player

object PersonGenerator {
    fun getPersons(names: List<String>, deck: Deck): Persons {
        val players = names.map { getPlayer(it, deck) }
        val dealer = getDealer(deck)

        return Persons(dealer, players)
    }

    private fun getPlayer(name: String, deck: Deck): Player {
        return makePlayer {
            name(name)
            addTwoCards(deck.getCard(), deck.getCard())
        }
    }

    private fun getDealer(deck: Deck): Dealer {
        return makeDealer {
            addTwoCards(deck.getCard(), deck.getCard())
        }
    }

    fun makePlayer(block: PersonBuilder.() -> Unit): Player {
        return DefaultPersonBuilder().apply(block).buildPlayer()
    }

    fun makeDealer(block: PersonBuilder.() -> Unit): Dealer {
        return DefaultPersonBuilder().apply(block).buildDealer()
    }
}
