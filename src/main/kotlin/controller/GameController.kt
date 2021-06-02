package controller

import domain.Winning
import domain.card.Cards
import domain.status.Finished
import domain.status.Running
import domain.user.Dealer
import domain.user.Gamers
import domain.user.User
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

        while (gamers.gamers.any { it -> it.status is Running } && dealer.status is Finished) {
            for (gamer: User in gamers.gamers) {
                if (view.guideContinueMessage(gamer).equals("y")) {
                    gamer.draw(deck)
                }
            }
            if (dealer.score() <= 16) {
                view.printDealerDraw()
                dealer.draw(deck)
            }
        }

        view.guideDealCard(gamers, 2)
        view.printPlayerCard(dealer)
        view.printPlayersCard(gamers)

        val winnerLog = Winning(dealer, gamers)
        view.printMatchResult(winnerLog, dealer, gamers)
    }
}