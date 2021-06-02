package controller

import domain.card.Cards
import domain.user.Gamers
import view.View

class GameController(val view: View) {
    val deck = Cards.createAllCards()
    fun playGame() {
        val gamers = Gamers(view.guideGameStart())

        for (i in 0 until 2) {
            gamers.dealCard(deck)
            gamers.dealCard(deck)
        }

    }
}