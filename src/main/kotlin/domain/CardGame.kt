package domain

import model.Cards
import model.Dealer
import model.Name
import model.Player
import model.Players

class CardGame(private val cardPack: Cards) {
    fun initPlayers(names: List<Name>): Players {
        return Players(names.map { Player(pickTwice(), it) })
    }

    fun initDealer(): Dealer {
        return Dealer(pickTwice())
    }

    fun pickTwice(): Cards = Cards(
        buildList {
            add(cardPack.pop())
            add(cardPack.pop())
        },
    )
}
