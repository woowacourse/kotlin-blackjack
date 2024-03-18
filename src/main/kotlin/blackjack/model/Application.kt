package blackjack.model

import blackjack.Dealer
import blackjack.Players
import blackjack.controller.BlackJackController

fun main() {
    val dealer = Dealer()
    val players = Players()
    val blackJackController = BlackJackController(dealer, players)
    blackJackController.startGame()
}
