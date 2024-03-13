package blackjack

import blackjack.controller.BlackJackController

fun main() {
    val blackJackController = BlackJackController()
    blackJackController.apply {
        startGameFlow()
        playGame()
        showResult()
    }
}
