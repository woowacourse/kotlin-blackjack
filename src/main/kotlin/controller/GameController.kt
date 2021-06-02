package controller

import domain.card.Cards
import domain.user.Dealer
import domain.user.Gamers
import view.View

class GameController(val view: View) {
    val deck = Cards.createAllCards()
    fun playGame() {
        val gamers = Gamers(view.guideGameStart())
        val dealer = Dealer("딜러")

        for (i in 0 until 2) {
            dealer.draw(deck)
            gamers.dealCard(deck)
        }

        view.guideDealCard(gamers, 2)
        view.printPlayerCard(dealer)
        view.printPlayersCard(gamers)
    }
}