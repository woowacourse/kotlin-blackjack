package domain

import model.CardDeck
import model.Cards
import model.Dealer
import model.Name
import model.Player
import model.Players

class CardGame(private val cardDeck: CardDeck) {
    fun initPlayers(names: List<Name>): Players {
        return Players(names.map { Player(pickTwice(), it) })
    }

    fun initDealer(): Dealer {
        return Dealer(pickTwice())
    }

    fun pickTwice(): Cards = Cards(
        buildList {
            add(cardDeck.drawCard())
            add(cardDeck.drawCard())
        },
    )
}
