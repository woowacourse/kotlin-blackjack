package domain.player

import domain.card.Card

class Player(val name: String, val bettingMoney: Int) {

    private var cards = PlayerCards()

    fun receive(card : Card){
        cards = cards.add(card)
    }
}
