package blackjack

import blackjack.controller.BlackJackController
import blackjack.model.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    BlackJackController(
        InputView(),
        OutputView(),
        CardDeck(),
    ).startGame()
}
