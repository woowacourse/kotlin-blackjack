package blackjack

import blackjack.controller.BlackJackController
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()

    BlackJackController(inputView, outputView).run()
}
