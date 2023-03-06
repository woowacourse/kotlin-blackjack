package domain

import model.CardPack
import model.Dealer
import model.Hand
import model.Names
import model.Player
import model.Players

class CardGame(private val cardPack: CardPack) {
    fun initPlayers(names: Names): Players {
        return Players(names.map { Player(pickTwice(), it) })
    }

    fun initDealer(): Dealer {
        return Dealer(pickTwice())
    }

    fun pickTwice(): Hand = Hand(
        buildList {
            add(cardPack.pop())
            add(cardPack.pop())
        },
    )
}
