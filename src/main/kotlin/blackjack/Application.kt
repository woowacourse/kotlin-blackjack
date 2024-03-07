package blackjack

import blackjack.controller.BlackJackController
import blackjack.model.CardDeck

fun main() {
    BlackJackController(
        CardDeck(),
    ).startGame()
}
