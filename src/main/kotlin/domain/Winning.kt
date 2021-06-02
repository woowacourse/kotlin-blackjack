package domain

import domain.status.Blackjack
import domain.user.Dealer
import domain.user.Gamers
import domain.user.User

class Winning(dealer: Dealer, gamers: Gamers, var dealerWin: Int = 0, var dealerLose: Int = 0) {
    val winningLog = HashMap<String, String>()

    init {

    }

    fun findWinner(dealer: Dealer, gamers: Gamers) {


        for (gamer: User in gamers.gamers) {
            if (dealer.score() > 21 || (dealer.score() < gamer.score() && gamer.score() <= 21) || dealer.status is Blackjack) {
                winningLog[gamer.name] = "승"
                dealerLose++
                continue
            }
            winningLog[gamer.name] = "패"
            dealerWin++
        }
        dealer.getScore()
    }
}