package blackjack

import blackjack.controller.BlackJackController
import blackjack.model.CardDeck

fun main() {
    val blackJackController = BlackJackController(CardDeck())
    blackJackController.apply {
        startGameFlow()
        playGame()
        displayGameResult()
    }
}
